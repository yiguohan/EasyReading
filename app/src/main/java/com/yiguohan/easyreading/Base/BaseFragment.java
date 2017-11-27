package com.yiguohan.easyreading.Base;


import android.content.Context;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.NetworkConnectedReceiver;
import com.yiguohan.easyreading.Views.INetworkConnectionView;

/**
 * A simple {@link Fragment} subclass.
 */
public class BaseFragment extends Fragment implements INetworkConnectionView {

    private NetworkConnectedReceiver networkConnectedReceiver;

    private IntentFilter intentFilter;

    protected Context mContext = EasyReadingApplication.getContext();

    public BaseFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_base, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        intentFilter = new IntentFilter();
        intentFilter.addAction(ConnectivityManager.CONNECTIVITY_ACTION);
        networkConnectedReceiver = new NetworkConnectedReceiver(this);
        getActivity().registerReceiver(networkConnectedReceiver,intentFilter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        getActivity().unregisterReceiver(networkConnectedReceiver);
    }

    @Override
    public void isOnLine() {

    }

    @Override
    public void isOffLine() {

    }
}
