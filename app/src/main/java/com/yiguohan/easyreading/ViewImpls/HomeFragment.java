package com.yiguohan.easyreading.ViewImpls;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguohan.easyreading.APIs.ApiFactory;
import com.yiguohan.easyreading.Beans.User;
import com.yiguohan.easyreading.MainActivity;
import com.yiguohan.easyreading.R;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {

    private static final String TAG = "HomeFragment";
    private Button button;
    private TextView textView;

    public HomeFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        button = (Button)view.findViewById(R.id.btn_test);
        textView = (TextView)view.findViewById(R.id.txt_test);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                User user = new User();
                user.setAccount("123123");
                user.setPassword("123123");
                ApiFactory.getDbService(getContext()).insertUser(user)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Consumer<Long>() {
                            @Override
                            public void accept(@NonNull Long aLong) throws Exception {
                                Log.d(TAG, "accept: " + aLong);
                            }
                        });
            }
        });
        return view;
    }

}
