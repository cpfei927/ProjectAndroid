package com.cpfei.view.viewpager;

import android.content.Context;
import android.graphics.PointF;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by cpfei on 2017/3/16.
 */

public class ChildViewPager extends ViewPager {

    /** 触摸时按下的点 **/

    PointF downP = new PointF();

    /** 触摸时当前的点 **/

    PointF curP = new PointF();

    public ChildViewPager(Context context) {
        super(context);
    }

    public ChildViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        if (getCurrentItem() == getChildCount()) {
            return super.onTouchEvent(ev);
        }

        if (getCurrentItem() == 0) {
            return super.onTouchEvent(ev);
        }

        curP.x = ev.getX();
        curP.y = ev.getY();

        if(ev.getAction() == MotionEvent.ACTION_DOWN) {
            //记录按下时候的坐标
            //切记不可用 downP = curP ，这样在改变curP的时候，downP也会改变
            downP.x = ev.getX();
            downP.y = ev.getY();
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        if(ev.getAction() == MotionEvent.ACTION_MOVE){
            //此句代码是为了通知他的父ViewPager现在进行的是本控件的操作，不要对我的操作进行干扰
            getParent().requestDisallowInterceptTouchEvent(true);
        }

        super.onTouchEvent(ev);

        return true;
    }
}
