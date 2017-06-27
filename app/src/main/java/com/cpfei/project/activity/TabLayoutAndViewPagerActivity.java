package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpfei.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * ViewPager和TabLayout实现联动
 */
public class TabLayoutAndViewPagerActivity extends AppCompatActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, TabLayoutAndViewPagerActivity.class);

        return intent;
    }


    private TabLayout tab_FindFragment_title;                            //定义TabLayout
    private ViewPager vp_FindFragment_pager;                             //定义viewPager
    private InnerPagerAdapter fAdapter;                               //定义adapter

    private List<Fragment> list_fragment;                                //定义要装fragment的列表
    private List<String> list_title;                                     //tab名称列表

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout_and_view_pager);
        initControls();
    }

    /**
     * 初始化各控件
     */
    private void initControls() {

        tab_FindFragment_title = (TabLayout) findViewById(R.id.tab_FindFragment_title);
        vp_FindFragment_pager = (ViewPager) findViewById(R.id.vp_FindFragment_pager);

        //初始化各fragment


        //将fragment装进列表中
        list_fragment = new ArrayList<>();
//        list_fragment.add(hotRecommendFragment);
//        list_fragment.add(hotCollectionFragment);
//        list_fragment.add(hotMonthFragment);
//        list_fragment.add(hotToday);

        //将名称加载tab名字列表，正常情况下，我们应该在values/arrays.xml中进行定义然后调用
        list_title = new ArrayList<>();
        list_title.add("热门推荐");
        list_title.add("热门收藏");
        list_title.add("本月热榜");
        list_title.add("本月收藏");
        list_title.add("今日要闻");
        list_title.add("今日热榜");

        //设置TabLayout的模式 MODE_FIXED -->屏幕宽度 TabLayout.MODE_SCROLLABLE -->左右可以滚动
        tab_FindFragment_title.setTabMode(TabLayout.MODE_SCROLLABLE);
        //为TabLayout添加tab名称
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(0)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(1)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(2)));
        tab_FindFragment_title.addTab(tab_FindFragment_title.newTab().setText(list_title.get(3)));

        fAdapter = new InnerPagerAdapter();

        //viewpager加载adapter
        vp_FindFragment_pager.setAdapter(fAdapter);
        //tab_FindFragment_title.setViewPager(vp_FindFragment_pager);
        //TabLayout加载viewpager
        tab_FindFragment_title.setupWithViewPager(vp_FindFragment_pager);
        //tab_FindFragment_title.set
    }



    class InnerPagerAdapter extends PagerAdapter {

        public InnerPagerAdapter() {

        }

        @Override
        public int getCount() {
            return list_title.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView textView = new TextView(TabLayoutAndViewPagerActivity.this);
            textView.setText(getPageTitle(position));
            container.addView(textView);
            return textView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return list_title.get(position % list_title.size());
        }
    }

}
