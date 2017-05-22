package com.yiguohan.easyreading.ViewImpls;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.yiguohan.easyreading.Base.BaseActivity;
import com.yiguohan.easyreading.MainActivity;
import com.yiguohan.easyreading.Presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.Utils.Util;
import com.yiguohan.easyreading.Views.IGetDataView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements IGetDataView{

     private static final int REQUEST_CODE = 1;

    private DatabasePresenter presenter;

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
        presenter.login(this,edt_account.getText().toString(),edt_password.getText().toString());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        presenter = new DatabasePresenter(this);
        ButterKnife.bind(this);
        Util.setStatuBarTransparent(this);
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

    @Override
    public void getDataSuccess(Cursor cursor) {
        if (cursor.getCount() ==1){
            cursor.moveToFirst();
            BaseActivity.userId = cursor.getString(cursor.getColumnIndex("id"));
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
            finish();
        }else {
            getDataFail();
        }
    }

    @Override
    public void getDataFail() {
        Toast.makeText(this,"账号或密码有误，请重新输入",Toast.LENGTH_LONG).show();
    }
}
