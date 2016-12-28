package com.cpfei.project.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.cpfei.project.R;

/**
 * 根据不同的图片个数显示不同的样式
 * 模仿朋友圈图片显示
 */
public class ImageListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_list);
    }
}
