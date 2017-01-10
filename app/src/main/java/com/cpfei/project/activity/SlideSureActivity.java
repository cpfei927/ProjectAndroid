package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.view.SlideSureView;

/**
 * 滑动验证
 */
public class SlideSureActivity extends AppCompatActivity {

    private SlideSureView mSSV;

    private SeekBar mSeekBar;

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SlideSureActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slide_sure);
        initView();
    }


    private void initView() {
        mSSV = (SlideSureView) findViewById(R.id.dy_v);
        mSeekBar = (SeekBar) findViewById(R.id.sb_dy);
        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mSSV.setUnitMoveDistance(mSSV.getAverageDistance(seekBar.getMax()) * i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                mSSV.testPuzzle();
            }
        });
        mSSV.setPuzzleListener(new SlideSureView.onPuzzleListener() {
            @Override
            public void onSuccess() {
                Toast.makeText(SlideSureActivity.this, "验证成功", Toast.LENGTH_SHORT).show();
                mSeekBar.setProgress(0);
                mSSV.reSet();
            }

            @Override
            public void onFail() {
                Toast.makeText(SlideSureActivity.this, "验证失败", Toast.LENGTH_SHORT).show();
                mSeekBar.setProgress(0);
            }
        });
    }


}
