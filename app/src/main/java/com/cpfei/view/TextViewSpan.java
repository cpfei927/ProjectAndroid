package com.cpfei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.UnderlineSpan;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * TextView的内容显示不同的字体颜色
 * 
 * @author cpfei
 * 
 */
@SuppressLint("NewApi")
public class TextViewSpan extends TextView {

	private Context mContext;
	private int textSpecialColor = Color.parseColor("#f8942c");
	private OnSpecialTextRespondListener specialTextRespondListener;

	public TextViewSpan(Context context) {
		this(context, null);
	}

	public TextViewSpan(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public TextViewSpan(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	private void init(Context context) {
		mContext = context;
		setBackgroundColor(Color.TRANSPARENT);
	}

	/**
	 * 设置字体的内容
	 * 
	 * @param textStr
	 * @param textSpecial 特殊字体
	 */
	public void setTextStr(String textStr, String textSpecial) {
		if (TextUtils.isEmpty(textStr)) {
			return;
		}
		if (!TextUtils.isEmpty(textSpecial) && textStr.indexOf(textSpecial) != -1) {
			int indexOf = textStr.indexOf(textSpecial);
			SpannableString spanStr = new SpannableString(textStr);
			spanStr.setSpan(
					new ForegroundColorSpan(textSpecialColor),
					indexOf, indexOf + textSpecial.length(),
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

			NoUnderlineSpan mNoUnderlineSpan = new NoUnderlineSpan();

//			spanStr.setSpan(new MyClickableSpan(), indexOf, indexOf + textSpecial.length(), Spanned.SPAN_MARK_MARK);

			spanStr.setSpan(mNoUnderlineSpan, indexOf, indexOf + textSpecial.length(),
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

			setText(spanStr);
			setMovementMethod(LinkMovementMethod.getInstance());
		} else {
			// 没有特殊字符
			setText(textStr);
		}
	}

	/**
	 * 设置字体的内容
	 *
	 * @param textStr
	 * @param startIndex 特殊字体 开始位置
	 * @param endIndex 特殊字体 结束位置
	 */
	public void setTextStr(String textStr, int startIndex, int endIndex) {
		if (TextUtils.isEmpty(textStr)) {
			return;
		}
		int length = textStr.length();
		if (startIndex > 0 && startIndex < length && endIndex > 0
				&& endIndex < length && startIndex < endIndex) {
			SpannableString spanStr = new SpannableString(textStr);
			spanStr.setSpan(new ForegroundColorSpan(textSpecialColor),
					startIndex, endIndex, Spannable.SPAN_EXCLUSIVE_INCLUSIVE);
			NoUnderlineSpan mNoUnderlineSpan = new NoUnderlineSpan();
			spanStr.setSpan(mNoUnderlineSpan, startIndex, endIndex,
					Spannable.SPAN_EXCLUSIVE_INCLUSIVE);

			setText(spanStr);
			setMovementMethod(LinkMovementMethod.getInstance());
		} else {
			// 没有特殊字符
			setText(textStr);
		}

	}

	class MyClickableSpan extends ClickableSpan {

		@Override
		public void onClick(View widget) {
			// TODO 接口回调，处理特定的需求
			if (specialTextRespondListener != null) {
				specialTextRespondListener.onSpecialTextRespond();
			}
		}

		@Override
		public void updateDrawState(TextPaint ds) {
			ds.setColor(textSpecialColor);
			ds.setUnderlineText(false);
		}
	}

	/**
	 * 设置特殊字体的颜色 (格式 #ff0000)
	 * 
	 */
	public void setSpecialTextColor(int resId) {
		this.textSpecialColor = resId;
	}

	/**
	 * 去掉特殊字体的下划线
	 * 
	 * @author Lotus
	 * 
	 */
	public class NoUnderlineSpan extends UnderlineSpan {

		@Override
		public void updateDrawState(TextPaint ds) {
			super.updateDrawState(ds);
			ds.setColor(textSpecialColor);
			ds.setUnderlineText(false);
		}
	}

	/**
	 * 接口回调处理特定行为
	 * 
	 * @author cpfei
	 * 
	 */
	public interface OnSpecialTextRespondListener {
		void onSpecialTextRespond();
	}

	public void setOnSpecialTextRespondListener(
			OnSpecialTextRespondListener listener) {
		this.specialTextRespondListener = listener;
	}

}
