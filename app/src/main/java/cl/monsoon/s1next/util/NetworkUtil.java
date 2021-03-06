package cl.monsoon.s1next.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import cl.monsoon.s1next.MyApplication;

public final class NetworkUtil {

    private NetworkUtil() {

    }

    public static boolean isWifiConnected() {
        ConnectivityManager connectivityManager =
                (ConnectivityManager)
                        MyApplication.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo =
                connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);

        return networkInfo.isConnected();
    }
}
