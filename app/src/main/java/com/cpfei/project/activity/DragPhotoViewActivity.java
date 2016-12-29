package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.cpfei.project.R;

/**
 * 根据不同的图片个数显示不同的样式
 * 模仿朋友圈图片显示
 */
public class DragPhotoViewActivity extends AppCompatActivity {

    private ImageView mImageView;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, DragPhotoViewActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_image_list);
        mImageView = (ImageView) findViewById(R.id.imageView);
    }


    public void onClick(View view) {
        startPhotoActivity(this, (ImageView) view);
    }


    public  void startPhotoActivity(Context context, ImageView imageView) {
        Intent intent = new Intent(context, DragPhotoActivity.class);
        int location[] = new int[2];

        imageView.getLocationOnScreen(location);
        intent.putExtra("left", location[0]);
        intent.putExtra("top", location[1]);
        intent.putExtra("height", imageView.getHeight());
        intent.putExtra("width", imageView.getWidth());

        context.startActivity(intent);
        overridePendingTransition(0,0);
    }


}
