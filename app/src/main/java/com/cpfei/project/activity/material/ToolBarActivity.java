package com.cpfei.project.activity.material;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import com.cpfei.project.R;
import com.cpfei.utils.IntentUtils;

/**
 * ToolBar使用
 * 用toolbar之前首先要将主题改为md风格的
 * Material Design的Theme
 * md的主题有：
 * •@Android:style/Theme.Material (dark version)
 * •@android:style/Theme.Material.Light (light version)
 * •@android:style/Theme.Material.Light.DarkActionBar
 * <p>
 * 与之对应的Compat Theme:
 * •Theme.AppCompat
 * •Theme.AppCompat.Light
 * •Theme.AppCompat.Light.DarkActionBar
 * 当然为了兼容低版本我们使用compat theme
 */
public class ToolBarActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, ToolBarActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tool_bar);
        findViews();
    }

    /**
     * toolBar详细设置查看工程目录下 image文件夹 toolbar.png
     */
    private void findViews() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        toolbar.setNavigationIcon(R.mipmap.btn_left_gray);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.option_1:
                IntentUtils.intent2WeChat(this);
                break;
            case R.id.option_2:
                break;
            case R.id.option_3:
                IntentUtils.intent2Market(this);
                break;
        }
        return true;
    }
}
