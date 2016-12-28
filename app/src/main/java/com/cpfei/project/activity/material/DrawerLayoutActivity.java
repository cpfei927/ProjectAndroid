package com.cpfei.project.activity.material;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.cpfei.project.R;

/**
 * DrawerLayout+NavigationView+ActionBarDrawerToggle
 *
 * 这里有两点要注意：
 * 1.主内容区的布局代码要放在侧滑菜单布局的前面,
 * 2.侧滑菜单的部分的布局需要设置android:layout_gravity=”start”属性，他表示侧滑菜单是在左边还是右边。
 * start表示左边 end表示右边
 * drawerLayout.openDrawer();
 * drawerLayout.closeDrawer();
 *
 * 全屏显示，简单解决办法
 * 去掉titleBar,布局中实现titleBar
 */
public class DrawerLayoutActivity extends AppCompatActivity {

    protected DrawerLayout mDrawerLayout;
    private Context mContext;
    protected NavigationView mNavigationView;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, DrawerLayoutActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drawer_layout);
        mContext = this;
        mDrawerLayout = ((DrawerLayout) findViewById(R.id.activity_drawer_layout));
        mNavigationView = (NavigationView)findViewById(R.id.navigation_view);

        setDrawerLayoutListener();
        setNavigationItemSelectedListener();
    }

    /**
     * ActionBarDrawerToggle
     * drawerLayout左侧菜单（或者右侧）的展开与隐藏可以被DrawerLayout.DrawerListener的实现监听到，
     * 不过还是建议用ActionBarDrawerToggle来监听，ActionBarDrawerToggle实现了DrawerListener，
     * 所以他能做DrawerListener可以做的任何事情，同时他还能将drawerLayout的展开和隐藏与actionbar的app 图标关联起来，
     * 当展开与隐藏的时候图标有一定的平移效果，点击图标的时候还能展开或者隐藏菜单。
     */
    private void setDrawerLayoutListener() {

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this, mDrawerLayout, R.string.open, R.string.close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                //打开
                Toast.makeText(mContext, "打开", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                //关闭
                Toast.makeText(mContext, "关闭", Toast.LENGTH_SHORT).show();
            }
        };
        mDrawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }


    public void onClick(View view) {
        switch (view.getId()){
            case R.id.open:
                boolean drawerOpen = mDrawerLayout.isDrawerOpen(mNavigationView);
                if (!drawerOpen) {
                    mDrawerLayout.openDrawer(mNavigationView);
                }
                break;

            case R.id.close:
                boolean drawerClose = mDrawerLayout.isDrawerOpen(mNavigationView);
                if (!drawerClose) {
                    mDrawerLayout.closeDrawer(mNavigationView);
                }
                break;
        }
    }


    private void setNavigationItemSelectedListener() {
        mNavigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                mDrawerLayout.closeDrawer(GravityCompat.START);
                switch (item.getItemId()) {
                    case R.id.nav_blog:
                        Toast.makeText(mContext, "我的博客", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_about:
                        Toast.makeText(mContext, "关于我", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_version:
                        Toast.makeText(mContext, "版本信息", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_sub1:
                        Toast.makeText(mContext, "副标题1", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.nav_sub2:
                        Toast.makeText(mContext, "副标题2", Toast.LENGTH_SHORT).show();
                        break;
                }
                return false;
            }
        });
    }


}
