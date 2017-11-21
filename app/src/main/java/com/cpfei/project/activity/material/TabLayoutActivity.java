package com.cpfei.project.activity.material;

import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TableLayout;
import android.widget.TextView;

import com.cpfei.project.R;

/**
 * http://www.jianshu.com/p/2b2bb6be83a8
 *
 * 可以配合TabItem使用高级效果
 */
public class TabLayoutActivity extends AppCompatActivity {

    protected TabLayout tabLayout;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, TabLayoutActivity.class);
        return intent;
    }

    String[] tab = {"精品", "耐克", "霸王别姬", "花花公子", "New Balance", "背靠背", "乔丹"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_layout);

        tabLayout = (TabLayout) findViewById(R.id.tabLayout);
        tabLayout.addTab(tabLayout.newTab().setText(tab[0]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[1]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[2]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[3]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[4]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[5]));
        tabLayout.addTab(tabLayout.newTab().setText(tab[6]));

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new ViewPagerAdapter());
        tabLayout.setupWithViewPager(viewPager);
//        tabLayout.getTabAt(4).select();// 手动选中


        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                //选中了tab的逻辑
                Log.d("Tag", "onTabSelected + " + tab.getText());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                //未选中tab的逻辑
                Log.d("Tag", "onTabUnselected + " + tab.getText());
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                //再次选中tab的逻辑
                Log.d("Tag", "onTabReselected + " + tab.getText());
            }
        });

    }


    public class ViewPagerAdapter extends PagerAdapter {

        @Override
        public CharSequence getPageTitle(int position) {
            return tab[position];
        }

        @Override
        public int getCount() {
            return tab.length;
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = LayoutInflater.from(TabLayoutActivity.this).inflate(R.layout.init_viewpager_view, container, false);
            ImageView image = (ImageView) view.findViewById(R.id.initViewPagerImage);
            image.setBackgroundColor(getResources().getColor(position % 2 == 0 ? R.color.color_2bc284 : R.color.color_4ea0ec));

            TextView textView = (TextView) view.findViewById(R.id.viewpagerText);
            textView.setText(tab[position]);

            container.addView(view);
            return view;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }
    }


}
