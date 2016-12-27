package com.cpfei.view;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * 可以设置使ViewPager能不能左右滑动（默认不可以）
 *
 */

public class ViewPagerNoScroll extends ViewPager {
	
	private boolean isCanScroll = false;
	public ViewPagerNoScroll(Context context) {
		super(context);
	}

	public ViewPagerNoScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public void setCanScroll(boolean isCanScroll) {
		this.isCanScroll = isCanScroll;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent arg0) {
		if (isCanScroll) {
			return super.onTouchEvent(arg0);
		} else {
			return false;
		}
	}
	
	@Override
	public boolean onInterceptTouchEvent(MotionEvent arg0) {
		if (isCanScroll) {
			return super.onInterceptTouchEvent(arg0);
		} else {
			return false;
		}
	}

	

}
