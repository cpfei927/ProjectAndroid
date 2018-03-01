package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cpfei.project.R;

public class OptionsActivity extends AppCompatActivity {

    public static Intent createIntent(Context context, View view) {
        Intent intent = new Intent(context, OptionsActivity.class);
        ActivityOptionsCompat compat = ActivityOptionsCompat.makeScaleUpAnimation(view,
                view.getWidth(), view.getHeight(), 0, 0);
        ActivityCompat.startActivity(context, new Intent(context, OptionsActivity.class),
                compat.toBundle());
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);
    }
}
