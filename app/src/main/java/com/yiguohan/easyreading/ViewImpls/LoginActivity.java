package com.yiguohan.easyreading.ViewImpls;

import android.content.Intent;
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

     private static final int REQUEST_CODE = 1;

    @BindView(R.id.edit_account_login)
    EditText edt_account;
    @BindView(R.id.edit_password_login)
    EditText edt_password;

    @OnClick(R.id.txt_signup)
    void signUp() {
        Intent intent = new Intent(LoginActivity.this,SignUpActivity.class);
        startActivityForResult(intent,REQUEST_CODE);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode){
            case REQUEST_CODE:
                if (resultCode == RESULT_OK){
                    String s = data.getStringExtra("Account");
                    edt_account.setText(data.getStringExtra("Account"));
                    edt_password.setText(data.getStringExtra("Password"));
                }
        }
    }
}
