package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cpfei.project.R;
import com.cpfei.project.adapter.InnerPagerAdapter;

public class ScrollViewViewPagerActivity extends AppCompatActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ScrollViewViewPagerActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_view_pager);

        ViewPager viewPager = (ViewPager) findViewById(R.id.sViewPager);

        InnerPagerAdapter innerPagerAdapter = new InnerPagerAdapter(this);

        viewPager.setAdapter(innerPagerAdapter);
    }
}
