package org.amahi.anywhere;

import org.amahi.anywhere.activity.AuthenticationActivity;
import org.amahi.anywhere.activity.NativeVideoActivity;
import org.amahi.anywhere.activity.NavigationActivity;
import org.amahi.anywhere.activity.OfflineFilesActivity;
import org.amahi.anywhere.activity.RecentFilesActivity;
import org.amahi.anywhere.activity.ServerAppActivity;
import org.amahi.anywhere.activity.ServerFileAudioActivity;
import org.amahi.anywhere.activity.ServerFileImageActivity;
import org.amahi.anywhere.activity.ServerFileVideoActivity;
import org.amahi.anywhere.activity.ServerFileWebActivity;
import org.amahi.anywhere.activity.ServerFilesActivity;
import org.amahi.anywhere.fragment.AudioListFragment;
import org.amahi.anywhere.fragment.NavigationFragment;
import org.amahi.anywhere.fragment.ServerAppsFragment;
import org.amahi.anywhere.fragment.ServerFileAudioFragment;
import org.amahi.anywhere.fragment.ServerFileDownloadingFragment;
import org.amahi.anywhere.fragment.ServerFileImageFragment;
import org.amahi.anywhere.fragment.ServerFilesFragment;
import org.amahi.anywhere.fragment.ServerSharesFragment;
import org.amahi.anywhere.fragment.SettingsFragment;
import org.amahi.anywhere.fragment.UploadSettingsFragment;
import org.amahi.anywhere.service.AudioService;
import org.amahi.anywhere.service.DownloadService;
import org.amahi.anywhere.service.UploadService;
import org.amahi.anywhere.service.VideoService;
import org.amahi.anywhere.task.AudioMetadataRetrievingTask;
import org.amahi.anywhere.tv.activity.TVWebViewActivity;
import org.amahi.anywhere.tv.activity.TvPlaybackAudioActivity;
import org.amahi.anywhere.tv.activity.TvPlaybackVideoActivity;
import org.amahi.anywhere.tv.fragment.MainTVFragment;
import org.amahi.anywhere.tv.fragment.ServerFileTvFragment;
import org.amahi.anywhere.tv.fragment.TvPlaybackAudioFragment;
import org.amahi.anywhere.tv.fragment.TvPlaybackVideoFragment;
import org.amahi.anywhere.util.UploadManager;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
abstract class ActivityModule {

    @ContributesAndroidInjector
    abstract AuthenticationActivity AuthenticationActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract NavigationActivity NavigationActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerAppActivity ServerAppActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract OfflineFilesActivity OfflineFilesActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFilesActivity ServerFilesActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFileAudioActivity ServerFileAudioActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFileImageActivity ServerFileImageActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFileVideoActivity ServerFileVideoActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract NativeVideoActivity NativeVideoActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract RecentFilesActivity RecentFilesActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFileWebActivity ServerFileWebActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract NavigationFragment NavigationFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerSharesFragment ServerSharesFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerAppsFragment ServerAppsFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFilesFragment ServerFilesFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract org.amahi.anywhere.fragment.ServerFileImageFragment ServerFileImageFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract org.amahi.anywhere.fragment.ServerFileAudioFragment ServerFileAudioFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFileDownloadingFragment ServerFileDownloadingFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract SettingsFragment SettingsFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract UploadSettingsFragment UploadSettingsFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract AudioListFragment AudioListFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract AudioService AudioServiceAndroidInjector();
    @ContributesAndroidInjector
    abstract VideoService VideoServiceAndroidInjector();
    @ContributesAndroidInjector
    abstract MainTVFragment MainTVFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract TVWebViewActivity TVWebViewActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract ServerFileTvFragment ServerFileTvFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract UploadService UploadServiceAndroidInjector();
    @ContributesAndroidInjector
    abstract DownloadService DownloadServiceAndroidInjector();
    @ContributesAndroidInjector
    abstract UploadManager UploadManagerAndroidInjector();
    @ContributesAndroidInjector
    abstract TvPlaybackVideoFragment TvPlaybackVideoFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract TvPlaybackVideoActivity TvPlaybackVideoActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract TvPlaybackAudioActivity TvPlaybackAudioActivityAndroidInjector();
    @ContributesAndroidInjector
    abstract TvPlaybackAudioFragment TvPlaybackAudioFragmentAndroidInjector();
    @ContributesAndroidInjector
    abstract AudioMetadataRetrievingTask AudioMetadataRetrievingTaskAndroidInjector();
}
