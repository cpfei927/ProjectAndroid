package com.cpfei.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * @decscription : 嵌套ScrollView 解决滑动冲突高度显示问题
 */
public class GridViewWithScroll extends GridView {
	public GridViewWithScroll(Context context) {
		super(context);

	}

	public GridViewWithScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	public GridViewWithScroll(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}
}
