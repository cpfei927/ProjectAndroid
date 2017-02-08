package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;

import com.cpfei.project.R;
import com.cpfei.project.activity.stereoview.ImageActivity;
import com.cpfei.project.activity.stereoview.LoginActivity;
import com.cpfei.project.activity.stereoview.SettingActivity;
import com.cpfei.view.animation.Rotate3dAnimation;

public class Rotate3dAnimationActivity extends AppCompatActivity implements View.OnClickListener {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, Rotate3dAnimationActivity.class);

        return intent;
    }


    protected ImageView mRotateImgv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rotate3d_animation);

        findViewById(R.id.rotate3dX).setOnClickListener(this);
        findViewById(R.id.rotate3dY).setOnClickListener(this);
        findViewById(R.id.rotate3dZ).setOnClickListener(this);



        mRotateImgv = ((ImageView) findViewById(R.id.rotateImgv));



        Button btnLogin = (Button) findViewById(R.id.btn_login);
        Button btnSetting = (Button) findViewById(R.id.btn_setting);
        Button btnImage = (Button) findViewById(R.id.btn_image);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rotate3dAnimationActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
        btnSetting.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rotate3dAnimationActivity.this, SettingActivity.class);
                startActivity(intent);
            }
        });
        btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Rotate3dAnimationActivity.this, ImageActivity.class);
                startActivity(intent);
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.rotate3dX:
                rotateOnXCoordinate();
                break;
            case R.id.rotate3dY:
                rotateOnYCoordinate();
                break;
            case R.id.rotate3dZ:
                rotateAnimHorizon();
                break;

        }
    }

    // 以X轴为轴心旋转
    private void rotateOnXCoordinate() {
        float centerX = mRotateImgv.getWidth() / 2.0f;
        float centerY = mRotateImgv.getHeight() / 2.0f;
        float depthZ = -0f;
        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(0, 80, centerX, centerY, depthZ, Rotate3dAnimation.ROTATE_X_AXIS, false);
        rotate3dAnimationX.setDuration(1000);
        rotate3dAnimationX.setFillAfter(true);// 保持到结束状态
        mRotateImgv.startAnimation(rotate3dAnimationX);
    }

    // 以Y轴为轴心旋转
    private void rotateOnYCoordinate() {
        float centerX = mRotateImgv.getWidth() / 2.0f;
        float centerY = mRotateImgv.getHeight() / 2.0f;
        float centerZ = 0f;

        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(0, 45, centerX, centerY, centerZ, Rotate3dAnimation.ROTATE_Y_AXIS, true);
        rotate3dAnimationX.setDuration(1000);
        rotate3dAnimationX.setFillAfter(true);// 保持到结束状态
        mRotateImgv.startAnimation(rotate3dAnimationX);
    }

    // 以Z轴为轴心旋转---等价于普通平面旋转动画
    private void rotateAnimHorizon() {
        float centerX = mRotateImgv.getWidth() / 2.0f;
        float centerY = mRotateImgv.getHeight() / 2.0f;
        float centerZ = 0f;

        Rotate3dAnimation rotate3dAnimationX = new Rotate3dAnimation(90, 0, centerX, centerY, centerZ, Rotate3dAnimation.ROTATE_Z_AXIS, true);
        rotate3dAnimationX.setDuration(1000);
        rotate3dAnimationX.setFillAfter(true);// 保持到结束状态
        mRotateImgv.startAnimation(rotate3dAnimationX);

        // 下面是使用android自带的旋转动画
        // RotateAnimation rotateAnimation = new RotateAnimation(0, 180, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
        // rotateAnimation.setDuration(1000);
        // mRotateImgv.startAnimation(rotateAnimation);
    }


}
