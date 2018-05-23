package com.cpfei.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cpfei.project.activity.BounceListViewActivity;
import com.cpfei.project.activity.CircleProgressBarActivity;
import com.cpfei.project.activity.DragPhotoViewActivity;
import com.cpfei.project.activity.DragRecyclerViewActivity;
import com.cpfei.project.activity.DragViewActivity;
import com.cpfei.project.activity.LyricActivity;
import com.cpfei.project.activity.MaterialDesignActivity;
import com.cpfei.project.activity.NumberActivity;
import com.cpfei.project.activity.PasswordActivity;
import com.cpfei.project.activity.PermissionActivity;
import com.cpfei.project.activity.PicassoActivity;
import com.cpfei.project.activity.PopupWindowActivity;
import com.cpfei.project.activity.RecyclerViewGridActivity;
import com.cpfei.project.activity.Rotate3dAnimationActivity;
import com.cpfei.project.activity.ScrollViewViewPagerActivity;
import com.cpfei.project.activity.SelectTextActivity;
import com.cpfei.project.activity.SlideSureActivity;
import com.cpfei.project.activity.TabLayoutAndViewPagerActivity;
import com.cpfei.project.activity.ViewIsVisibleActivity;
import com.cpfei.project.activity.ViewPagerActivity;
import com.cpfei.project.activity.ViewPagerTransformerActivity;
import com.cpfei.project.activity.WebViewActivity;
import com.cpfei.project.activity.OptionsActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {

    protected ArrayList<String> strings;
    protected ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViews();
    }

    private void findViews() {

        strings = new ArrayList<>();

        strings.add("MaterialDesign");
        strings.add("PhotoView");
        strings.add("RecyclerGridView");
        strings.add("SlideSureView");
        strings.add("circleProgressBar");
        strings.add("PermissionActivity");
        strings.add("Rotate3dAnimation");
        strings.add("Picasso");
        strings.add("ViewPager");
        strings.add("DragView");
        strings.add("viewPagerTransformer");
        strings.add("BounceListView");
        strings.add("WebView");
        strings.add("自定义隐藏密码");
        strings.add("TabLayoutViewPager");
        strings.add("NumberView数字增加动画");
        strings.add("ActivityOptions");
        strings.add("ScrollView嵌套ViewPager");
        strings.add("LyricTextView 歌词渐变");
        strings.add("选择文本");
        strings.add("判断View是否在屏幕中显示");
        strings.add("PopupWindow");
        strings.add("DragRecyclerView");

        listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> ada = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, strings);
        listView.setAdapter(ada);
        listView.setOnItemClickListener(this);

    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        switch (position){
            case 0:
                startActivity(MaterialDesignActivity.createIntent(this));
                break;
            case 1:
                startActivity(DragPhotoViewActivity.createIntent(this));
                break;
            case 2:
                startActivity(RecyclerViewGridActivity.createIntent(this));
                break;
            case 3:
                startActivity(SlideSureActivity.createIntent(this));
                break;
            case 4:
                startActivity(CircleProgressBarActivity.createIntent(this));
                break;
            case 5:
                startActivity(PermissionActivity.createIntent(this));
                break;
            case 6:
                startActivity(Rotate3dAnimationActivity.createIntent(this));
                break;
            case 7:
                startActivity(PicassoActivity.createIntent(this));
                break;
            case 8:
                startActivity(ViewPagerActivity.createIntent(this));
                break;
            case 9:
                startActivity(DragViewActivity.createIntent(this));
                break;
            case 10:
                startActivity(ViewPagerTransformerActivity.createIntent(this));
                break;
            case 11:
                startActivity(BounceListViewActivity.createIntent(this));
                break;
            case 12:
                startActivity(WebViewActivity.createIntent(this));
                break;
            case 13:
                startActivity(PasswordActivity.createIntent(this));
                break;
            case 14:
                startActivity(TabLayoutAndViewPagerActivity.createIntent(this));
                break;
            case 15:
                startActivity(NumberActivity.createIntent(this));
                break;
            case 16:
                OptionsActivity.createIntent(this, listView);
                break;
            case 17:
                startActivity(ScrollViewViewPagerActivity.createIntent(this));
                break;
            case 18:
                startActivity(LyricActivity.createIntent(this));
                break;
            case 19:
                startActivity(SelectTextActivity.createIntent(this));
                break;
            case 20:
                startActivity(ViewIsVisibleActivity.createIntent(this));
                break;
            case 21:
                startActivity(PopupWindowActivity.createIntent(this));
                break;
            case 22:
                startActivity(DragRecyclerViewActivity.createIntent(this));
                break;
        }

    }
}
