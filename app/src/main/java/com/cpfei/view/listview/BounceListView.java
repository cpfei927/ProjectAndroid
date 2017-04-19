package com.cpfei.view.listview;

import android.content.Context;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.ListView;

/**
 * Created by cpfei on 2017/4/14.
 */

public class BounceListView extends ListView {

    protected static final int MAX_Y_OVERSCROLL_DISTANCE = 500;

    protected int mMaxYOverScrollDistance;

    protected Context mContext;

    public BounceListView(Context context) {
        this(context, null);
    }

    public BounceListView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public BounceListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        initBounceListView();
    }


    private void initBounceListView(){
        //get the density of the screen and do some maths with it on the max overscroll distance
        //variable so that you get similar behaviors no matter what the screen size

        final DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
        final float density = metrics.density;

        mMaxYOverScrollDistance = (int) (density * MAX_Y_OVERSCROLL_DISTANCE);
    }


    @Override
    protected boolean overScrollBy(int deltaX, int deltaY, int scrollX, int scrollY, int scrollRangeX,
                                   int scrollRangeY, int maxOverScrollX, int maxOverScrollY, boolean isTouchEvent) {
        if (!isTouchEvent) { // 禁止惯性滑动
            if ((scrollY < 0 && deltaX < 0)
                    || (scrollY > getHeight() && deltaX > 0)) {
                deltaY = 0;
            }
        }
        return super.overScrollBy(deltaX, (deltaY + 1) / 2, scrollX, scrollY,
                scrollRangeX, scrollRangeY, maxOverScrollX, mMaxYOverScrollDistance, isTouchEvent);
    }
}
