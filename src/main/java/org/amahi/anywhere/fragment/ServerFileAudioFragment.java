package org.amahi.anywhere.fragment;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.squareup.otto.Subscribe;

import org.amahi.anywhere.AmahiApplication;
import org.amahi.anywhere.R;
import org.amahi.anywhere.bus.AudioMetadataRetrievedEvent;
import org.amahi.anywhere.bus.BusProvider;
import org.amahi.anywhere.server.client.ServerClient;
import org.amahi.anywhere.server.model.ServerFile;
import org.amahi.anywhere.server.model.ServerShare;
import org.amahi.anywhere.task.AudioMetadataRetrievingTask;
import org.amahi.anywhere.util.BlurBuilder;
import org.amahi.anywhere.util.Downloader;
import org.amahi.anywhere.util.Fragments;

import javax.inject.Inject;

import dagger.android.AndroidInjection;

/**
 * Audio fragment. Shows a single audio cover image.
 */

public class ServerFileAudioFragment extends Fragment {
    @Inject
    ServerClient serverClient;

    @Override
    public View onCreateView(LayoutInflater layoutInflater, ViewGroup container, Bundle savedInstanceState) {
        return layoutInflater.inflate(R.layout.fragment_server_file_audio, container, false);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        setUpInjections();

        setUpAudioMetadata();
    }

    private void setUpInjections() {
        AndroidInjection.inject(getActivity());
        AmahiApplication.from(getActivity()).inject(this);
    }

    private void setUpAudioMetadata() {
        if (getShare() != null) {
            AudioMetadataRetrievingTask
                .newInstance(getActivity(), getAudioUri(), getFile())
                .setImageView(getImageView())
                .setBackgroundView(getBackgroundLayoutView())
                .execute();
        } else {
            // offline File

            new AudioMetadataRetrievingTask(getActivity(), getAudioPath(), getFile())
                .setImageView(getImageView())
                .setBackgroundView(getBackgroundLayoutView())
                .execute();
        }
    }

    private Uri getAudioUri() {
        return serverClient.getFileUri(getShare(), getFile());
    }

    private String getAudioPath() {
        return getActivity().getFilesDir() + "/" + Downloader.OFFLINE_PATH + "/" + getFile().getName();
    }

    @Subscribe
    public void onAudioMetadataRetrieved(AudioMetadataRetrievedEvent event) {
        ImageView imageView = event.getImageView();
        FrameLayout backgroundLayout = event.getBackgroundLayout();
        Bitmap bitmap = event.getAudioMetadata().getAudioAlbumArt();
        if (imageView != null && backgroundLayout != null) {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                Bitmap blurred = BlurBuilder.blur(getContext(), bitmap);
                backgroundLayout.setBackground(new BitmapDrawable(getResources(), blurred));
            } else {
                imageView.setImageResource(R.drawable.default_audiotrack);
                imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            }
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        BusProvider.getBus().register(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        BusProvider.getBus().unregister(this);
    }

    private ServerShare getShare() {
        return getArguments().getParcelable(Fragments.Arguments.SERVER_SHARE);
    }

    private ServerFile getFile() {
        return getArguments().getParcelable(Fragments.Arguments.SERVER_FILE);
    }

    public ImageView getImageView() {
        return (ImageView) getView().findViewById(R.id.image_album_art);
    }

    public FrameLayout getBackgroundLayoutView() {
        return (FrameLayout) getView().findViewById(R.id.layout_audio);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

        tearDownImageContent();
    }

    private void tearDownImageContent() {
        getImageView().setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        getImageView().setImageBitmap(BitmapFactory.decodeResource(getResources(), R.drawable.default_audiotrack));
    }

}
