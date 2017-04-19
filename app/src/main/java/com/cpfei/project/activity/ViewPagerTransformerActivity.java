package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpfei.project.R;

import java.util.ArrayList;

public class ViewPagerTransformerActivity extends AppCompatActivity {

    protected ViewPager viewpager;
    private int[] images = {R.mipmap.p10, R.mipmap.p11, R.mipmap.p12, R.mipmap.p14};

    private ArrayList<ImageView> imageViews = new ArrayList<>(images.length);


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ViewPagerTransformerActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_transformer);

        viewpager = ((ViewPager) findViewById(R.id.viewpager));

        // 定义一个布局并设置参数
        LinearLayout.LayoutParams mParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT);


        // 初始化引导图片列表
        for (int i = 0; i < images.length; i++) {
            ImageView iv = new ImageView(this);
            iv.setLayoutParams(mParams);
            // 防止图片不能填满屏幕
            iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
            // 加载图片资源
            iv.setImageResource(images[i]);
            imageViews.add(iv);
        }


        ViewPagerTransformerActivity.InnerPagerAdapter innerPagerAdapter =
                new ViewPagerTransformerActivity.InnerPagerAdapter();
        viewpager.setAdapter(innerPagerAdapter);

        viewpager.setPageTransformer(true, new MyPagerTransformer());

    }

    class InnerPagerAdapter extends PagerAdapter {

        public InnerPagerAdapter() {

        }

        @Override
        public int getCount() {
            return imageViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            container.addView(imageViews.get(position));
            return imageViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


    class MyPagerTransformer implements ViewPager.PageTransformer {

        private static final float ROT_MAX = 20.0f;
        private float mRot;

        @Override
        public void transformPage(View view, float position) {


            if (position < -1) {
                //界面不可见
                ViewCompat.setRotation(view, 0);
            } else if (position <= 1) {
                // 界面可见
                mRot = (ROT_MAX * position);
                ViewCompat.setPivotX(view, view.getMeasuredWidth() * 0.5f);
                ViewCompat.setPivotY(view, view.getMeasuredHeight());
                ViewCompat.setRotation(view, mRot);
            } else {
                //界面不可见
                ViewCompat.setRotation(view, 0);
            }


        }
    }


}
