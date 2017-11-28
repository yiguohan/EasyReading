package com.yiguohan.easyreading.viewImpls;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

import com.yiguohan.easyreading.base.BaseActivity;
import com.yiguohan.easyreading.beans.User;
import com.yiguohan.easyreading.presenters.DatabasePresenter;
import com.yiguohan.easyreading.R;
import com.yiguohan.easyreading.utils.Util;
import com.yiguohan.easyreading.views.IInsertDataView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class SignUpActivity extends BaseActivity implements IInsertDataView {

    private DatabasePresenter presenter;

    @BindView(R.id.edit_account_signUp)
    EditText edt_account;
    @BindView(R.id.edit_password_signUp)
    EditText edt_password;
    @BindView(R.id.edit_password_signUpCheck)
    EditText edt_passwordCheck;

    @OnClick(R.id.btn_RegisterAccount)
    void register() {
        if (edt_account.getText().toString().isEmpty() || edt_password.getText().toString().isEmpty()||edt_passwordCheck.getText().toString().isEmpty()){
            Toast.makeText(this, "信息未填完整，请继续输入", Toast.LENGTH_LONG).show();
            return;
        }
        if (!presenter.doubleCheckPassword(edt_password.getText().toString(), edt_passwordCheck.getText().toString())) {
            Toast.makeText(this, "两次输入的密码不一致，请重新确认", Toast.LENGTH_LONG).show();
            return;
        }
        presenter.checkAccount(this, edt_account.getText().toString());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        ButterKnife.bind(this);
        Util.setStatuBarTransparent(this);
        presenter = new DatabasePresenter(this);
    }

    @Override
    public void insertDataSuccess(long num) {
        if (num <= 0) {
            insertDataFail();
            return;
        }
        Intent intent = new Intent();
        intent.putExtra("Account", edt_account.getText().toString());
        intent.putExtra("Password", edt_password.getText().toString());
        this.setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void insertDataFail() {
        Toast.makeText(this, "未注册成功", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void checkData(boolean isSuccess) {
        if (!isSuccess) {
            Toast.makeText(this, "账号已存在", Toast.LENGTH_SHORT).show();
        } else {
            User user = new User();
            user.setAccount(edt_account.getText().toString());
            user.setPassword(edt_password.getText().toString());
            presenter.insertUser(SignUpActivity.this, user);
        }
    }
}
