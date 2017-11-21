package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cpfei.project.R;
import com.cpfei.project.activity.material.CoordinatorLayoutActivity;
import com.cpfei.project.activity.material.DrawerLayoutActivity;
import com.cpfei.project.activity.material.TabLayoutActivity;
import com.cpfei.project.activity.material.ToolBarActivity;

/**
 * CardView
 */
public class MaterialDesignActivity extends AppCompatActivity implements View.OnClickListener {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, MaterialDesignActivity.class);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_material_design);
        findViews();
    }

    private void findViews() {
        findViewById(R.id.toolBar).setOnClickListener(this);
        findViewById(R.id.drawerLayout).setOnClickListener(this);
        findViewById(R.id.coordinatorLayout).setOnClickListener(this);
        findViewById(R.id.tabLayout).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.toolBar:
                startActivity(ToolBarActivity.createIntent(this));
                break;
            case R.id.drawerLayout:
                startActivity(DrawerLayoutActivity.createIntent(this));
                break;
            case R.id.coordinatorLayout:
                startActivity(CoordinatorLayoutActivity.createIntent(this));
                break;
            case R.id.tabLayout:
                startActivity(TabLayoutActivity.createIntent(this));
                break;
        }
    }
}
