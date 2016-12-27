package com.cpfei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ScrollView;

/**
 * Created by cpfei on 2015/9/17.
 * ScrollView 滑动监听
 */
public class ScrollViewScroll extends ScrollView {

    private ScrollViewListener scrollViewListener = null;

    public ScrollViewScroll(Context context) {
        super(context);
    }

    public ScrollViewScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ScrollViewScroll(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void setScrollViewListener(ScrollViewListener scrollViewListener) {
        this.scrollViewListener = scrollViewListener;
    }

    @Override
    protected void onScrollChanged(int x, int y, int oldx, int oldy) {
        super.onScrollChanged(x, y, oldx, oldy);
        if (scrollViewListener != null) {
            scrollViewListener.onScrollChanged(this, x, y, oldx, oldy);
        }
    }

    public interface ScrollViewListener {
        void onScrollChanged(ScrollViewScroll scrollView, int x, int y, int oldx, int oldy);
    }

}
