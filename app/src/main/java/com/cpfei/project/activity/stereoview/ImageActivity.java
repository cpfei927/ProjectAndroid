package com.cpfei.project.activity.stereoview;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.cpfei.bean.BroadcastNewBaseInfo;
import com.cpfei.project.R;
import com.cpfei.view.custom.SquareNationMsgView;

import java.util.LinkedList;

/**
 * Created by Mr_immortalZ on 2016/7/15.
 * email : mr_immortalz@qq.com
 */
public class ImageActivity extends AppCompatActivity {
    protected SquareNationMsgView mSquareNationMsgView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);


        mSquareNationMsgView = ((SquareNationMsgView) findViewById(R.id.mSquareNationMsgView));

        LinkedList<BroadcastNewBaseInfo> broadcastNewBaseInfos = new LinkedList<>();
        for (int i = 0; i < 100; i++){

            BroadcastNewBaseInfo broadcastNewBaseInfo = new BroadcastNewBaseInfo();

            broadcastNewBaseInfo.setDescriptionText("Item ==== " + i);
            broadcastNewBaseInfo.setJumpUrl("Url === " + i);

            broadcastNewBaseInfos.add(broadcastNewBaseInfo);
        }

        mSquareNationMsgView.setData(broadcastNewBaseInfos);

        mSquareNationMsgView.startAnim();


    }
}
