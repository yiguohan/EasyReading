package com.yiguohan.easyreading.ViewImpls;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity {

    @BindView(R.id.edit_account_signUp)
    EditText edt_account;
    @BindView(R.id.edit_password_signUp) EditText edt_password;
    @BindView(R.id.edit_password_signUpCheck) EditText edt_passwordCheck;
    @OnClick(R.id.btn_RegisterAccount) void register(){
        Intent intent = new Intent();
        intent.putExtra("Account",edt_account.getText());
        intent.putExtra("Password",edt_password.getText());
        this.setResult(RESULT_OK,intent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        if(Build.VERSION.SDK_INT >= 21){
            View decorView = getWindow().getDecorView();
            int option = View.SYSTEM_UI_FLAG_FULLSCREEN|View.SYSTEM_UI_FLAG_LAYOUT_STABLE;
            decorView.setSystemUiVisibility(option);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
    }
}
