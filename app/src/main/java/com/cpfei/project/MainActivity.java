package com.cpfei.project;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.cpfei.project.activity.CircleProgressBarActivity;
import com.cpfei.project.activity.DragPhotoViewActivity;
import com.cpfei.project.activity.MaterialDesignActivity;
import com.cpfei.project.activity.PermissionActivity;
import com.cpfei.project.activity.PicassoActivity;
import com.cpfei.project.activity.RecyclerViewGridActivity;
import com.cpfei.project.activity.Rotate3dAnimationActivity;
import com.cpfei.project.activity.SlideSureActivity;
import com.cpfei.project.activity.ViewPagerActivity;
import com.cpfei.project.activity.material.ToolBarActivity;
import com.squareup.picasso.Picasso;

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
        findViewById(R.id.recyclerGridView).setOnClickListener(this);
        findViewById(R.id.slideSureView).setOnClickListener(this);
        findViewById(R.id.circleProgressBar).setOnClickListener(this);
        findViewById(R.id.permissionActivity).setOnClickListener(this);
        findViewById(R.id.rotate3dAnimation).setOnClickListener(this);
        findViewById(R.id.picasso).setOnClickListener(this);
        findViewById(R.id.viewPager).setOnClickListener(this);

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
            case R.id.recyclerGridView:
                startActivity(RecyclerViewGridActivity.createIntent(this));
                break;
            case R.id.slideSureView:
                startActivity(SlideSureActivity.createIntent(this));
                break;
            case R.id.circleProgressBar:
                startActivity(CircleProgressBarActivity.createIntent(this));
                break;
            case R.id.permissionActivity:
                startActivity(PermissionActivity.createIntent(this));
                break;
            case R.id.rotate3dAnimation:
                startActivity(Rotate3dAnimationActivity.createIntent(this));
                break;
            case R.id.picasso:
                startActivity(PicassoActivity.createIntent(this));
                break;
            case R.id.viewPager:
                startActivity(ViewPagerActivity.createIntent(this));
                break;

        }
    }
}
