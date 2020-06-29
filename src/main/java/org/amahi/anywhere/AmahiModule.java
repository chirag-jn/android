/*
 * Copyright (c) 2014 Amahi
 *
 * This file is part of Amahi.
 *
 * Amahi is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Amahi is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Amahi. If not, see <http ://www.gnu.org/licenses/>.
 */

package org.amahi.anywhere;

import android.app.Application;
import android.content.Context;

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
import org.amahi.anywhere.cache.CacheModule;
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
import org.amahi.anywhere.server.ApiModule;
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

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.Module;
import dagger.Provides;
import dagger.android.AndroidInjectionModule;

/**
 * Application dependency injection module. Includes {@link org.amahi.anywhere.server.ApiModule} and
 * provides application's {@link android.content.Context} for possible consumers.
 */
@Singleton
@Component(
    modules = {
        AndroidInjectionModule.class,
        ApiModule.class,
        CacheModule.class,
        ActivityModule.class
    }
)
public interface AmahiModule {

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        Builder apiModule(ApiModule apiModule);
        Builder cacheModule(CacheModule cacheModule);
        AmahiModule build();
    }

    void inject(AmahiApplication amahiApplication);

    void inject(AuthenticationActivity authenticationActivity);

    void inject(NavigationActivity navigationActivity);

    void inject(ServerAppActivity serverAppActivity);

    void inject(OfflineFilesActivity offlineFilesActivity);

    void inject(ServerFilesActivity serverFilesActivity);

    void inject(ServerFileAudioActivity serverFileAudioActivity);

    void inject(ServerFileImageActivity serverFileImageActivity);

    void inject(ServerFileVideoActivity serverFileVideoActivity);

    void inject(NativeVideoActivity nativeVideoActivity);

    void inject(RecentFilesActivity recentFilesActivity);

    void inject(ServerFileWebActivity serverFileWebActivity);

    void inject(NavigationFragment navigationFragment);

    void inject(ServerSharesFragment serverSharesFragment);

    void inject(ServerAppsFragment serverAppsFragment);

    void inject(ServerFilesFragment serverFilesFragment);

    void inject(ServerFileImageFragment serverFileImageFragment);

    void inject(ServerFileAudioFragment serverFileAudioFragment);

    void inject(ServerFileDownloadingFragment serverFileDownloadingFragment);

    void inject(SettingsFragment settingsFragment);

    void inject(UploadSettingsFragment uploadSettingsFragment);

    void inject(AudioListFragment audioListFragment);

    void inject(AudioService audioService);

    void inject(VideoService videoService);

    void inject(MainTVFragment mainTVFragment);

    void inject(TVWebViewActivity tvWebViewActivity);

    void inject(ServerFileTvFragment serverFileTvFragment);

    void inject(UploadService uploadService);

    void inject(DownloadService downloadService);

    void inject(UploadManager uploadManager);

    void inject(TvPlaybackVideoFragment tvPlaybackVideoFragment);

    void inject(TvPlaybackVideoActivity tvPlaybackVideoActivity);

    void inject(TvPlaybackAudioActivity tvPlaybackAudioActivity);

    void inject(TvPlaybackAudioFragment tvPlaybackAudioFragment);

    void inject(AudioMetadataRetrievingTask audioMetadataRetrievingTask);
//    void inject(Object injectionsConsumer);

//    private final Application application;
//
//    public AmahiModule(Application application) {
//        this.application = application;
//    }
//
//    @Provides
//    @Singleton
//    Context provideContext() {
//        return application;
//    }
}
