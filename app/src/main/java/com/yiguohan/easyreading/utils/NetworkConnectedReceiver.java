package com.yiguohan.easyreading.utils;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.yiguohan.easyreading.base.BaseActivity;
import com.yiguohan.easyreading.base.BaseFragment;
import com.yiguohan.easyreading.views.INetworkConnectionView;

import static android.content.Context.CONNECTIVITY_SERVICE;

public class NetworkConnectedReceiver extends BroadcastReceiver {
    private Context mContext;

    private INetworkConnectionView connectionView;

    public NetworkConnectedReceiver(INetworkConnectionView networkConnectionView) {

        connectionView = networkConnectionView;
        if (connectionView instanceof BaseActivity) {
            mContext = ((BaseActivity) networkConnectionView);
        } else if (connectionView instanceof BaseFragment) {
            mContext = ((BaseFragment) networkConnectionView).getContext();
        }
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            connectionView.isOnLine();
        } else {
            connectionView.isOffLine();
        }
    }
}
