package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.view.PasswordView;

public class PasswordActivity extends AppCompatActivity implements PasswordView.PasswordListener {


    protected TextView passwordTv;
    protected PasswordView passwordView;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, PasswordActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        passwordTv = ((TextView) findViewById(R.id.password));

        passwordView = (PasswordView) findViewById(R.id.passwordView);

        passwordView.setPasswordListener(this);

    }

    @Override
    public void passwordChange(String changeText) {

    }

    @Override
    public void passwordComplete() {
        Toast.makeText(this, "输入完成", Toast.LENGTH_SHORT).show();
        passwordTv.setText(passwordView.getPassword());
    }

    @Override
    public void keyEnterPress(String password, boolean isComplete) {
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(this, "请输入", Toast.LENGTH_SHORT).show();
            return;
        }


        passwordTv.setText(password);
    }
}
