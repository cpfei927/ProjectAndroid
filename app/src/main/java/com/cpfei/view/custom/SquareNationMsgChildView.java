package com.cpfei.view.custom;

import android.content.Context;
import android.graphics.Paint;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.cpfei.bean.BroadcastNewBaseInfo;
import com.cpfei.project.R;


/**
 * Created by Administrator on 2017/1/17
 * 首页全国消息
 */
public class SquareNationMsgChildView extends FrameLayout {
    private static final String TAG = "SquareNationMsgChildView";
    private Context mContext;

    public SquareNationMsgChildView(Context context) {
        super(context);
        mContext = context;
    }

    public SquareNationMsgChildView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
    }

    public SquareNationMsgChildView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    private MsgDefatltHolder mDefatltHolder;
    private void initDefatltView()
    {
        View view =  LayoutInflater.from(getContext()).inflate(R.layout.square_nation_msg_default_item, null);
        mDefatltHolder = new MsgDefatltHolder(view);
        addView(view);

    }

    public void setDefaultData(final BroadcastNewBaseInfo info)
    {
        if(mDefatltHolder == null)
        {
            initDefatltView();
        }
        mDefatltHolder.itemView.setVisibility(View.VISIBLE);

        mDefatltHolder.tv_msg.setText(info.getDescriptionText());
        mDefatltHolder.tv_detail.setText(info.getJumpUrl());


//        SpannableStringBuilder spannable = SquareBroadcastManager.getInstance()
//                .setNationSpannableStringBuilder(mContext, info, mDefatltHolder.tv_msg, 23);
//        //添加点击事件
//        spannable.setSpan(new ClickableSpan() {
//            @Override
//            public void onClick(View widget) {
//                onClickView(info);
//            }
//            @Override
//            public void updateDrawState(TextPaint ds) {
//                //super.updateDrawState(ds);
//            }
//        },0,spannable.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        if(info != null && (!TextUtils.isEmpty(info.getUrl())
//                            || info.getEventType() == EVENT_TYPE_NO.EVENT_TYPE_BRO_RECV_RP.getValue()
//                            || info.getEventType() == EVENT_TYPE_NO.EVENT_TYPE_BRO_ROB_RP.getValue()))
//        {
//            mDefatltHolder.tv_detail.setVisibility(View.VISIBLE);
//        }else
//        {
//            mDefatltHolder.tv_detail.setVisibility(View.GONE);
//        }
    }
    static class MsgDefatltHolder
    {
        View itemView;
        TextView tv_msg;
        TextView tv_detail;
        public MsgDefatltHolder(View v)
        {
            itemView = v;
            tv_msg = (TextView) v.findViewById(R.id.tv_msg);
            tv_detail = (TextView) v.findViewById(R.id.tv_detail);
            tv_detail.getPaint().setFlags(Paint.UNDERLINE_TEXT_FLAG); //下划线
        }
    }



    public void processMsg(final BroadcastNewBaseInfo info)
    {
        if(info == null)
        {
            return;
        }
        setDefaultData(info);


        this.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickView(info);
            }
        });

    }


    private void onClickView(BroadcastNewBaseInfo info)
    {
        if(info == null)
        {
            return;
        }
//        if(TextUtils.isEmpty(info.getUrl()))
//        {
//            if(info.getEventType() == EVENT_TYPE_NO.EVENT_TYPE_BRO_RECV_RP.getValue()
//                    || info.getEventType() == EVENT_TYPE_NO.EVENT_TYPE_BRO_ROB_RP.getValue())
//            {
//                ShowWebUtil.getInstance().loadUrlActivity(mContext, ShowWebType.SHOW_RED_PACKET_DETAIL, "", "");
//
//            }else if (!NpcUser.isNpc(info.getHostUserId())) {
//                AnonymousUser.showAnonymousDetailActivity(mContext, info.getHostUserId());
//            } else if(info.getHostUserId() > 0){
//                IntentUtil.startStarCardDialogActivity(mContext, Config.getInstance().getUserID(), NpcUser.getRoleId(info.getHostUserId()));
//            }
//        }else
//        {
//            ShowWebUtil.getInstance().loadUrlActivity(info.getUrl(), "",mContext);
//        }
    }

}
