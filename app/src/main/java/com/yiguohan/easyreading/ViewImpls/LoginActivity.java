package com.yiguohan.easyreading.ViewImpls;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity {

    @BindView(R.id.edit_account_login)
    EditText edt_account;
    @BindView(R.id.edit_password_login)
    EditText edt_password;

    @OnClick(R.id.txt_signup)
    void signUp() {
        Toast.makeText(this, "Sign Up", Toast.LENGTH_SHORT).show();
    }


    @OnClick(R.id.btn_Login)
    void submit() {
        Toast.makeText(this, "Login", Toast.LENGTH_SHORT).show();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
    }
}
