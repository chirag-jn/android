package org.amahi.anywhere.fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import org.amahi.anywhere.R;
import org.amahi.anywhere.db.repositories.FileInfoRepository;
import org.amahi.anywhere.util.Constants;
import org.amahi.anywhere.util.Fragments;

import java.io.File;

public class AlertDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    File file;
    private int dialogType = -1;
    private String fileUniqueKey;
    AlertDialog.Builder builder;
    public static final int DELETE_FILE_DIALOG = 0;
    public static final int DUPLICATE_FILE_DIALOG = 1;
    public static final int SIGN_OUT_DIALOG = 3;
    public static final int FILE_INFO_DIALOG = 2;

    private String parentStr;
    private String nameStr;
    private String dateStr;
    private String sizeStr;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        builder = new android.app.AlertDialog.Builder(getActivity());

        if (getArguments() != null) {
            dialogType = getArguments().getInt(Fragments.Arguments.DIALOG_TYPE);
            fileUniqueKey = getArguments().getString(Fragments.Arguments.FILE_UNIQUE_KEY);
        }

        switch (dialogType) {
            case DELETE_FILE_DIALOG:
                buildDeleteDialog();
                break;

            case DUPLICATE_FILE_DIALOG:
                buildDuplicateDialog();
                break;

            case FILE_INFO_DIALOG:
                if (getArguments() != null) {
                    nameStr = getArguments().getString(Fragments.Arguments.FILE_NAME);
                    parentStr = getArguments().getString(Fragments.Arguments.PARENT_FOLDER);
                    sizeStr = getFileSize();
                    dateStr = getArguments().getString(Fragments.Arguments.FILE_DATE);
                }
                buildFileInfoDialog();
                break;

            case SIGN_OUT_DIALOG:
                buildSignOutDialog();
        }

        return builder.create();
    }

    private void buildDeleteDialog() {
        builder.setTitle(getString(R.string.message_delete_file_title))
            .setMessage(getString(R.string.message_delete_file_body))
            .setPositiveButton(getString(R.string.button_yes), this)
            .setNegativeButton(getString(R.string.button_no), this);
    }

    private void buildDuplicateDialog() {
        file = (File) getArguments().getSerializable("file");
        builder.setTitle(getString(R.string.message_duplicate_file_upload))
            .setMessage(getString(R.string.message_duplicate_file_upload_body, file.getName()))
            .setPositiveButton(getString(R.string.button_yes), this)
            .setNegativeButton(getString(R.string.button_no), this);
    }

    private void buildFileInfoDialog() {
        View view = getActivity().getLayoutInflater().inflate(R.layout.file_info_dialog, null);
        setFileInfoView(view);
        builder.setTitle(getString(R.string.title_file_info))
            .setPositiveButton(getString(R.string.text_ok), this);
        builder.setView(view);
    }

    private void setFileInfoView(View view) {
        TextView lastOpened = view.findViewById(R.id.text_last_opened);
        TextView name = view.findViewById(R.id.text_name);
        TextView date = view.findViewById(R.id.text_date);
        TextView size = view.findViewById(R.id.text_size);
        TextView parent = view.findViewById(R.id.text_folder);
        name.setText(nameStr);
        date.setText(dateStr);
        size.setText(sizeStr);
        parent.setText(parentStr);
        lastOpened.setText(getFileLastOpened());
    }

    private String getFileSize() {
        if(getArguments()!=null) {
            float size = Float.parseFloat(getArguments().getString(Fragments.Arguments.FILE_SIZE));
            size = (size/1000)/1000;
            return size + " " + Constants.MB;
        }
        return getString(R.string.empty);
    }

    private String getFileLastOpened() {
        FileInfoRepository fileInfoRepository = new FileInfoRepository(getContext());
        if (fileInfoRepository.getFileInfo(fileUniqueKey) == null) {
            return getString(R.string.never_opened);
        }
        return fileInfoRepository.getFileInfo(fileUniqueKey).getLastOpened();
    }

    private void buildSignOutDialog() {
        builder.setTitle(getString(R.string.sign_out_title))
            .setMessage(getString(R.string.sign_out_message))
            .setPositiveButton(getString(R.string.sign_out_title), this)
            .setNegativeButton(getString(R.string.cancel), this);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

        if (dialogType == DUPLICATE_FILE_DIALOG) {
            DuplicateFileDialogCallback callback = getDuplicateDialogCallback();

            if (callback != null) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                    callback.dialogPositiveButtonOnClick(file);
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    dialog.dismiss();
                    callback.dialogNegativeButtonOnClick();
                }
            }

        } else if (dialogType == DELETE_FILE_DIALOG) {
            DeleteFileDialogCallback callback = getDeleteDialogCallback();

            if (callback != null) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                    callback.dialogPositiveButtonOnClick();
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    dialog.dismiss();
                    callback.dialogNegativeButtonOnClick();
                }
            }
        } else if (dialogType == SIGN_OUT_DIALOG) {
            SignOutDialogCallback callback = getSignOutDialogCallback();

            if (callback != null) {
                if (which == DialogInterface.BUTTON_POSITIVE) {
                    dialog.dismiss();
                    callback.dialogPositiveButtonOnClick();
                } else if (which == DialogInterface.BUTTON_NEGATIVE) {
                    dialog.dismiss();
                    callback.dialogNegativeButtonOnClick();
                }
            }
        }


    }

    private DuplicateFileDialogCallback getDuplicateDialogCallback() {
        DuplicateFileDialogCallback callback;
        if (getTargetFragment() != null) {
            try {
                callback = (DuplicateFileDialogCallback) getTargetFragment();
            } catch (ClassCastException e) {
                Log.e(this.getClass().getSimpleName(), "Callback of this class must be implemented by target fragment!", e);
                throw e;
            }
        } else {
            try {
                callback = (DuplicateFileDialogCallback) getActivity();
            } catch (ClassCastException e) {
                Log.e(this.getClass().getSimpleName(), "Callback of this class must be implemented by the activity!", e);
                throw e;
            }
        }
        return callback;
    }

    private DeleteFileDialogCallback getDeleteDialogCallback() {
        DeleteFileDialogCallback callback;
        if (getTargetFragment() != null) {
            try {
                callback = (DeleteFileDialogCallback) getTargetFragment();
            } catch (ClassCastException e) {
                Log.e(this.getClass().getSimpleName(), "Callback of this class must be implemented by target fragment!", e);
                throw e;
            }
        } else {
            try {
                callback = (DeleteFileDialogCallback) getActivity();
            } catch (ClassCastException e) {
                Log.e(this.getClass().getSimpleName(), "Callback of this class must be implemented by the activity!", e);
                throw e;
            }
        }
        return callback;
    }

    private SignOutDialogCallback getSignOutDialogCallback() {

        SignOutDialogCallback callback;
        if (getTargetFragment() != null) {
            try {
                callback = (SignOutDialogCallback) getTargetFragment();
            } catch (ClassCastException e) {
                Log.e(this.getClass().getSimpleName(), "Callback of this class must be implemented by target fragment!", e);
                throw e;
            }
        } else {
            try {
                callback = (SignOutDialogCallback) getActivity();
            } catch (ClassCastException e) {
                Log.e(this.getClass().getSimpleName(), "Callback of this class must be implemented by the activity!", e);
                throw e;
            }
        }
        return callback;
    }


    public interface DuplicateFileDialogCallback {

        void dialogPositiveButtonOnClick(File file);


        void dialogNegativeButtonOnClick();
    }

    public interface DeleteFileDialogCallback {

        void dialogPositiveButtonOnClick();


        void dialogNegativeButtonOnClick();
    }

    public interface SignOutDialogCallback {

        void dialogPositiveButtonOnClick();


        void dialogNegativeButtonOnClick();
    }

}
