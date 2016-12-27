package com.cpfei.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpfei.project.R;

/**
 * Created by cpfei on 2016/12/6.
 * 默认显示三行，点击可展开折叠的textView
 */

public class TextViewMore extends LinearLayout implements View.OnClickListener, Animator.AnimatorListener {

    private static final int TYPE_ADD = 0;
    private static final int TYPE_DECREASE = 1;
    private static final int DEFAULT_MAX_LINES = 3;

    private static final long ANIMAT_DURATION = 0;

    private Context mContext;
    private LayoutInflater mInflater;
    protected TextView mMoreFeatureTxtv;
    private boolean mIsChecked;
    private int lineCount = 0;
    protected TextView mMoreFeatureBtn;

    public TextViewMore(Context context) {
        this(context, null);
    }

    public TextViewMore(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextViewMore(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        mInflater = LayoutInflater.from(mContext);
        initView();
    }

    private void initView() {
        View view = mInflater.inflate(R.layout.layout_more_text_view, this, true);
        mMoreFeatureTxtv = ((TextView) view.findViewById(R.id.freeProductInfoMoreFeatureTxtv));
        mMoreFeatureBtn = ((TextView) view.findViewById(R.id.freeProductInfoMoreFeatureBtn));
        mMoreFeatureBtn.setOnClickListener(this);
        mMoreFeatureBtn.setVisibility(GONE);
    }

    public void setText(String txt) {
        if (TextUtils.isEmpty(txt)) {
            return;
        }

        mMoreFeatureBtn.setVisibility(VISIBLE);
        mMoreFeatureTxtv.setText(txt);
        mMoreFeatureTxtv.setMaxLines(DEFAULT_MAX_LINES);

        mMoreFeatureTxtv.post(new Runnable() {
            @Override
            public void run() {
                lineCount = mMoreFeatureTxtv.getLineCount();
                if (lineCount > DEFAULT_MAX_LINES) {
                    mMoreFeatureBtn.setVisibility(VISIBLE);
                } else {
                    mMoreFeatureBtn.setVisibility(GONE);
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        if (!mIsChecked) {
            // 向下动画
            playAnimat(DEFAULT_MAX_LINES, lineCount, TYPE_ADD);
        } else {
            playAnimat(lineCount, DEFAULT_MAX_LINES + 1, TYPE_DECREASE);
        }
    }

    synchronized void playAnimat(int start, int end, final int type) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);
        animator.setDuration(ANIMAT_DURATION);
        animator.setInterpolator(new DecelerateInterpolator());
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Integer temp = (Integer) animation.getAnimatedValue();
                if (type == TYPE_ADD) {
                    mMoreFeatureTxtv.setMaxLines(temp + 1);
                } else if (type == TYPE_DECREASE) {
                    mMoreFeatureTxtv.setMaxLines(temp - 1);
                }
            }
        });
        animator.addListener(this);
        animator.start();
    }


    @Override
    public void onAnimationStart(Animator animation) {
        if (!mIsChecked) {
            mIsChecked = true;
            mMoreFeatureBtn.setText("收起更多特色");
        } else {
            mIsChecked = false;
            mMoreFeatureBtn.setText("查看更多特色");
        }
    }

    @Override
    public void onAnimationEnd(Animator animation) {

    }

    @Override
    public void onAnimationCancel(Animator animation) {

    }

    @Override
    public void onAnimationRepeat(Animator animation) {

    }
}
