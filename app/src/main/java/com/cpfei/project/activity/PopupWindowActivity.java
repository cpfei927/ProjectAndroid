package com.cpfei.project.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.SimpleAdapter;

import com.cpfei.project.R;
import com.cpfei.utils.LogUtil;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PopupWindowActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, PopupWindowActivity.class);
    }


    private Button button;
    private PopupWindow popupWindow;
    private ListView listView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_popup_window);
        initControls();
    }


    private void initControls() {
        LayoutInflater inflater = LayoutInflater.from(this);
        final View view = inflater.inflate(R.layout.listview_popup, null);

        SimpleAdapter adapter = new SimpleAdapter(this, getData(),
                R.layout.item_title,
                new String[]{"text"},
                new int[]{R.id.title});
        listView = (ListView) view.findViewById(R.id.listview);
        listView.setAdapter(adapter);

        //自适配长、框设置
        popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(R.mipmap.bg) );
        popupWindow.setOutsideTouchable(true);
        popupWindow.setAnimationStyle(android.R.style.Animation_Dialog);
        popupWindow.update();
        popupWindow.setTouchable(true);
        popupWindow.setFocusable(true);

        button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NewApi")
            @Override
            public void onClick(View v) {
                if (!popupWindow.isShowing()) {
//                    listView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    view.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED);
                    int measuredHeight = view.getMeasuredHeight();
                    LogUtil.d("Tag", "measuredHeight = " + measuredHeight);

                    popupWindow.showAsDropDown(button, 0, - measuredHeight * 6);
//                    popupWindow.showAtLocation(button, Gravity.BOTTOM, 0, 0);
                }
            }
        });
    }


    private List<Map<String, String>> getData() {
        List<Map<String, String>> list = new ArrayList<Map<String, String>>();

        Map<String, String> map = new HashMap<String, String>();
        map.put("text", "中国");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("text", "加油");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("text", "钓鱼岛是中国的");
        list.add(map);

        map = new HashMap<String, String>();
        map.put("text", "！！");
        list.add(map);
        return list;
    }


}
