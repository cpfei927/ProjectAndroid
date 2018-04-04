package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;
import android.widget.TextView;

import com.cpfei.project.R;

/**
 * Android View的可见性检查方法
 * http://unclechen.github.io/2016/10/17/Android%20View%E7%9A%84%E5%8F%AF%E8%A7%81%E6%80%A7%E6%A3%80%E6%9F%A5%E6%96%B9%E6%B3%95-%E4%B8%8A%E7%AF%87/
 */
public class ViewIsVisibleActivity extends AppCompatActivity implements View.OnClickListener {

    protected View view;
    protected TextView textView;
    protected View ScrollTitleView;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ViewIsVisibleActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_is_visible);

        view = findViewById(R.id.view);
        textView = ((TextView) findViewById(R.id.viewText));

        findViewById(R.id.isShown).setOnClickListener(this);
        findViewById(R.id.GlobalVisRect).setOnClickListener(this);
        findViewById(R.id.LocalVisRect).setOnClickListener(this);

        ScrollTitleView = findViewById(R.id.ScrollTitleView);
        final ScrollView scrollView = (ScrollView) findViewById(R.id.scrollView);
        scrollView.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {

                // 可以先判断ScrollView中的mView是不是在屏幕中可见
                Rect scrollBounds = new Rect();
                scrollView.getHitRect(scrollBounds);
                boolean localVisibleRect = ScrollTitleView.getLocalVisibleRect(scrollBounds);
                textView.setText("localVisibleRect = " + localVisibleRect + "\n");
//                textView.append("left , top = (" + rect.left + "," + rect.top + ")\n");
//                textView.append("bottom , right = (" + rect.bottom + "," + rect.right + ")\n");
            }
        });




    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.isShown:
                boolean shown = view.isShown();
                textView.setText("isShow = " + shown);
                break;
            case R.id.GlobalVisRect:

                Rect rect = new Rect();
                boolean globalVisibleRect = view.getGlobalVisibleRect(rect);
                textView.setText("globalVisibleRect = " + globalVisibleRect + "\n");
                textView.append("left , top = (" + rect.left + "," + rect.top + ")\n");
                textView.append("bottom , right = (" + rect.bottom + "," + rect.right + ")\n");

                break;
            case R.id.LocalVisRect:
                Rect rect1 = new Rect();
                boolean localVisibleRect = view.getLocalVisibleRect(rect1);
                textView.setText("localVisibleRect = " + localVisibleRect + "\n");
                textView.append("left , top = (" + rect1.left + "," + rect1.top + ")\n");
                textView.append("bottom , right = (" + rect1.bottom + "," + rect1.right + ")\n");
                break;
        }
    }
}
