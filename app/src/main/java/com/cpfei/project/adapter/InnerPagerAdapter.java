package com.cpfei.project.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpfei.project.R;

/**
 * Created by cpfei on 2018/2/27.
 */

public class InnerPagerAdapter extends PagerAdapter {

    private final Context context;

    public InnerPagerAdapter(Context context) {
        this.context = context;

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

        View view = LayoutInflater.from(context).inflate(R.layout.layout_viewpager, container,false);

        TextView textView = (TextView) view.findViewById(R.id.item);

        textView.setText("Parent = " + position);

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

}
