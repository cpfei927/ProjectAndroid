package com.cpfei.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cpfei.project.activity.DragPhotoViewActivity;
import com.cpfei.project.activity.MaterialDesignActivity;
import com.cpfei.project.activity.material.ToolBarActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViews();
    }

    private void findViews() {
        findViewById(R.id.materialDesign).setOnClickListener(this);
        findViewById(R.id.photoView).setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.materialDesign:
                startActivity(MaterialDesignActivity.createIntent(this));
                break;
            case R.id.photoView:
                startActivity(DragPhotoViewActivity.createIntent(this));
                break;
        }
    }
}
