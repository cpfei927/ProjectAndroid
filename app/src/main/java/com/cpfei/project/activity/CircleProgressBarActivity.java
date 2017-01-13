package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.view.progress.CircleProgressBarView;
import com.cpfei.view.progress.DutyView;

public class CircleProgressBarActivity extends AppCompatActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, CircleProgressBarActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_circle_progress_bar);

        final CircleProgressBarView circleProgressBarView = (CircleProgressBarView) findViewById(R.id.circleProgressBarView);


        findViewById(R.id.percentAdd).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleProgressBarView.setPercent(circleProgressBarView.getPercent() + 5);
            }
        });

        findViewById(R.id.percentJian).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                circleProgressBarView.setPercent(circleProgressBarView.getPercent() -5);
            }
        });



        DutyView dutyView = (DutyView) findViewById(R.id.dutyView);

        dutyView.setOnDutyChangedListener(new DutyView.onDutyChangedListener() {

            @Override
            public void onDutyChanged(int currentDuty) {
                Toast.makeText(CircleProgressBarActivity.this, "current duty = "+currentDuty, Toast.LENGTH_SHORT)
                        .show();
            }
        });






    }
}
