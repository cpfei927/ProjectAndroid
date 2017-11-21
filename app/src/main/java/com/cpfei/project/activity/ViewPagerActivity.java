package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpfei.project.R;
import com.cpfei.view.viewpager.ChildViewPager;

/**
 * 解决两个ViewPager的滑动冲突
 */
public class ViewPagerActivity extends AppCompatActivity {

    protected ViewPager viewpager;


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ViewPagerActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager);

        viewpager = ((ViewPager) findViewById(R.id.viewpager));

        viewpager.setOffscreenPageLimit(5);

        InnerPagerAdapter innerPagerAdapter = new InnerPagerAdapter();

        viewpager.setAdapter(innerPagerAdapter);

    }

    class InnerPagerAdapter extends PagerAdapter {

        public InnerPagerAdapter() {

        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            View view = LayoutInflater.from(ViewPagerActivity.this).inflate(R.layout.layout_viewpager, container,false);

            TextView textView = (TextView) view.findViewById(R.id.item);

            textView.setText("Parent = " + position);

            ViewPager childViewPager = (ViewPager) view.findViewById(R.id.childViewPager);

            ChildPagerAdapter childPagerAdapter = new ChildPagerAdapter();

            childViewPager.setAdapter(childPagerAdapter);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }



    class ChildPagerAdapter extends PagerAdapter {

        int[] ints = {R.color.color_4ea0ec, R.color.color_2b2b2b, R.color.color_7744cc};


        public ChildPagerAdapter() {

        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {

            TextView view = new TextView(ViewPagerActivity.this);

            view.setText("Child  == " + position);


            ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

            view.setLayoutParams(layoutParams);

            view.setBackgroundColor(ints[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }




}
