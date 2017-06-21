package com.yiguohan.easyreading.Base;

import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.NetworkConnectedReceiver;
import com.yiguohan.easyreading.Views.INetworkConnectionView;

public class BaseActivity extends AppCompatActivity implements INetworkConnectionView {
    private NetworkConnectedReceiver networkConnectedReceiver;

    private IntentFilter intentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base);
        ActivityCollector.addActivity(this);
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkConnectedReceiver = new NetworkConnectedReceiver(this);
        registerReceiver(networkConnectedReceiver,intentFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
        unregisterReceiver(networkConnectedReceiver);
    }

    @Override
    public void isOnLine() {

    }

    @Override
    public void isOffLine() {
        Toast.makeText(this, "糟糕，网络无法加载了！", Toast.LENGTH_SHORT).show();
    }
}
