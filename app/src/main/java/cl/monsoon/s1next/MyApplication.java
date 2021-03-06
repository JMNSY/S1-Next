package cl.monsoon.s1next;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.StrictMode;
import android.preference.PreferenceManager;

import com.bugsnag.android.Bugsnag;

import net.danlew.android.joda.JodaTimeAndroid;

import cl.monsoon.s1next.singleton.Config;

public final class MyApplication extends Application {

    private static Context sContext;

    public static Context getContext() {
        return sContext;
    }

    @Override
    public void onCreate() {
        // enable StrictMode when debug
        if (BuildConfig.DEBUG) {
            StrictMode.setThreadPolicy(
                    new StrictMode.ThreadPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
            StrictMode.setVmPolicy(
                    new StrictMode.VmPolicy.Builder()
                            .detectAll()
                            .penaltyLog()
                            .build());
        }

        sContext = getApplicationContext();

        Bugsnag.init(this);

        JodaTimeAndroid.init(this);

        SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        // initiate the config depends on settings
        Config.setCurrentTheme(sharedPreferences);
        Config.setTextScale(sharedPreferences);
        Config.setAvatarCacheInvalidationInterval(sharedPreferences);
        Config.setAvatarsDownloadStrategy(sharedPreferences);
        Config.setAvatarResolutionStrategy(sharedPreferences);
        Config.setImagesDownloadStrategy(sharedPreferences);
    }
}
