package com.cpfei.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.cpfei.project.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Number View with animation to display a number
 * NumberView numberView = (NumberView) findViewById(R.id.numView);
 * numberView.setFloatNumberText(50, 300);
 * numberView.play();
 * 执行完setNumberText方法后执行play()否则不会执行动画
 */
public class NumberView extends View {
    private String mNumberString = "";
    private String mStartNumberString = "";

    private Context mContext;

    private TextPaint mTextPaint;
    private Typeface mTypeface;

    private float mTextWidth;
    private float mTextHeight;
    private float mTextSize = 32;    // textSize
    private float mCharWidth;       // character space
    private float mExtraSpace;      // character space

    private int ANIM_TIME = 1000;
    private int mNumberColor = Color.BLACK; // textColor
    private boolean mNumberGravityLeft = false; //是否左对齐
    private List<ValueAnimator> animatorList = new ArrayList<>();
    private List<Integer> startNumber = new ArrayList<>();

    private float mCurShowCount;
    private int maxNumChangeIndex;// 最高位的变化的 index
    private int maxNumChangeCount;//最高位变化数量

    public NumberView(Context context) {
        super(context);
        mContext = context;
        init(null, 0);
    }

    public NumberView(Context context, AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
        init(attrs, 0);
    }

    public NumberView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mContext = context;
        init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        final TypedArray a = getContext().obtainStyledAttributes(attrs, R.styleable.NumberView, defStyle, 0);
        mNumberColor = a.getColor(R.styleable.NumberView_numberColor, mNumberColor);
        mExtraSpace = a.getDimension(R.styleable.NumberView_charSpace, mExtraSpace);
        mTextSize = a.getDimension(R.styleable.NumberView_text_Size, mTextSize);
        if (a.hasValue(R.styleable.NumberView_number)) {
            mNumberString = a.getString(R.styleable.NumberView_number);
        }
        mNumberGravityLeft = a.getBoolean(R.styleable.NumberView_number_gravity_left, false);
        a.recycle();

        // Set up a default TextPaint object
        mTextPaint = new TextPaint();
        mTextPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextAlign(Paint.Align.LEFT);

        //设置字体
//        AssetManager mgr = mContext.getAssets();
//        mTypeface = Typeface.createFromAsset(mgr, "fonts/Roboto-Medium.ttf");

        // Update TextPaint and text measurements from attributes
        invalidateTextPaintAndMeasurements();
    }

    private float pointTextWidth;

    private void invalidateTextPaintAndMeasurements() {
        mTextPaint.setTextSize(mTextSize);
        mTextPaint.setColor(mNumberColor);
//        mTextPaint.setTypeface(mTypeface);
        mTextWidth = mTextPaint.measureText(mNumberString);
        mCharWidth = mTextWidth / mNumberString.length();
        pointTextWidth = mTextPaint.measureText(".");
        Paint.FontMetrics fontMetrics = mTextPaint.getFontMetrics();
        mTextHeight = fontMetrics.descent;
        startNumber.clear();
        for (int i = 0; i < mNumberString.length(); i++) {
            char c = mNumberString.charAt(i);
            if (c >= '0' && c <= '9') {
                int num = Integer.parseInt("" + c);
                startNumber.add(i, calcNum(num, 5));
            } else {
                startNumber.add(i, 0);
            }
        }
    }

    private int calcNum(int num, int i) {
        // num 0 ~ 9
        int result = 0;
        if (num + i > 9) {
            result = num + i - 10;
        } else if (num + i < 0) {
            result = num + i + 10;
        } else {
            result = num + i;
        }

        return result;
    }

    private int calcNum2(int num, int i, int endnum) {
        // num 0 ~ 9
        int result = 0;
        if (num + i > 9) {
            result = num + i - 10;
        } else if (num + i < 0) {
            result = endnum;
        } else {
            result = num + i;
        }

        return result;
    }

    private int calcNum0(int num, int i, int endNum) {
        int n = endNum - i;
        if (n < 0) {
            n = 0;
        }
        return n;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.UNSPECIFIED || heightMode == MeasureSpec.AT_MOST) {
            heightSize = (int) mTextSize;
        }

        setMeasuredDimension(widthSize, heightSize);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        // super.onDraw(canvas);
        // allocations per draw cycle.
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getWidth() - paddingLeft - paddingRight;
        int contentHeight = getHeight() - paddingTop - paddingBottom;
        float startX = 0;
        if (!mNumberGravityLeft) {
            startX = paddingLeft + (contentWidth - mTextWidth) - mCharWidth / 2;
            startX -= mExtraSpace * (startNumber.size() - 1) / 2;
        }
        float startY = 0;
        boolean end = true;
        for (ValueAnimator valueAnimator : animatorList) {
            if (valueAnimator.isRunning()) {
                end = false;
            }
        }

        if (isDefault && end) {
            float textWidth = mTextPaint.measureText(mDefaultCount + "");
            float x = 0;
            if (!mNumberGravityLeft) {
                x = paddingLeft + (contentWidth - textWidth) - mCharWidth / 2;
            }
            canvas.drawText(mDefaultCount + "", x, mTextSize - mTextHeight / 2, mTextPaint);
            return;
        }
        // progress : 0f ~ 1f
        // dy : 0 ~ (mTextHeight * mNumberString.length()-1)
        for (int i = 0; i < startNumber.size() && i < mNumberString.length(); i++) {

            char ch = mNumberString.charAt(i);
            if (!(ch >= '0' && ch <= '9')) {// 是小数点“.”时
                startX += pointTextWidth / 4;
                canvas.drawText(String.valueOf(ch), startX, mTextSize - mTextHeight / 2, mTextPaint);
                startX += pointTextWidth + mExtraSpace;
                continue;
            }
            int endNum = Integer.parseInt("" + ch);
            if (i < maxNumChangeIndex) {// 比如1231和1298 前两位相同（12），先显示没有滚动动画
                canvas.drawText(endNum + "", startX, mTextSize - mTextHeight / 2, mTextPaint);
                startX += mCharWidth + mExtraSpace;
                continue;
            }

            // 需要滚动的数字 start
            float progress = 1;
            if (i < animatorList.size()) {
                try {
                    progress = (Float) animatorList.get(i).getAnimatedValue();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            Integer num = startNumber.get(i);
            if (i == maxNumChangeIndex) {
                int dy = (int) ((mTextSize * maxNumChangeCount) * progress);
                startY = mTextSize - mTextHeight / 2 - dy;

                for (int j = 0; j <= maxNumChangeCount; j++) {
                    int cal = calcNum0(maxNumChangeCount, maxNumChangeCount - j, endNum);
                    String c = "";
                    if (maxNumChangeIndex == 0 && cal == 0) {
                        //对进位时且是最高位时 从0滚到1做处理
                        c = " ";
                    } else {
                        c = String.valueOf(cal);
                    }
                    canvas.drawText(c, startX, startY, mTextPaint);
                    startY += mTextSize;
                }
            } else {
                int dy = (int) ((mTextSize * 5) * progress);
                startY = mTextSize - mTextHeight / 2 - dy;
                for (int j = 0; j < 6; j++) {
                    String c = "" + calcNum2(num, -j, endNum);
                    canvas.drawText(c, startX, startY, mTextPaint);
                    startY += mTextSize;
                }
            }
            startX += mCharWidth + mExtraSpace;
        }

        if (!end) {
            invalidate();
        }
    }

    public void play() {
        // 数字相同时不需要启动动画
        if (TextUtils.isEmpty(mStartNumberString)
                || TextUtils.isEmpty(mNumberString)
                || mStartNumberString.equals(mNumberString)) {
            return;
        }

        for (ValueAnimator valueAnimator : animatorList) {
            valueAnimator.cancel();
        }

        animatorList.clear();
        for (int i = 0; i < mNumberString.length(); i++) {
            ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1).setDuration(ANIM_TIME + 100 * i);
            valueAnimator.start();
            animatorList.add(0, valueAnimator);
        }
        isDefault = false;
        invalidate();
    }

    /**
     * return the number
     *
     * @return the number
     */
    public int getNumber() {
        if (mNumberString == null || mNumberString.length() <= 0) {
            return 0;
        }
        return Integer.parseInt(mNumberString);
    }

    /**
     * set the number to show
     *
     * @param number the number to show
     */
    public void setNumberText(long startNum, long number) {
        startNum = Math.abs(startNum);
        mStartNumberString = String.valueOf(startNum);
        mNumberString = String.valueOf(number).trim();
        changeNum();
        invalidateTextPaintAndMeasurements();
    }

    public void setFloatNumberText(float startNum, float number) {
        startNum = Math.abs(startNum);
        mCurShowCount = number;
        mStartNumberString = String.format("%.2f", startNum);
        mNumberString = String.format("%.2f", number);
        changeNum();
        invalidateTextPaintAndMeasurements();
    }

    private void changeNum() {
        if (mNumberString.length() <= 0 || mStartNumberString.length() <= 0) {
            return;
        }
        if (mNumberString.equals(mStartNumberString)) {
            maxNumChangeCount = 0;
            maxNumChangeIndex = -1;
        } else if (mNumberString.length() == mStartNumberString.length()) {
            for (int i = 0; i < mNumberString.length(); i++) {
                char mc = mNumberString.charAt(i);
                char sc = mStartNumberString.charAt(i);
                if (mc != sc) {
                    maxNumChangeIndex = i;
                    maxNumChangeCount = mc - sc;
                    break;
                }
            }
        } else {
            char mc = mNumberString.charAt(0);
            if (mc >= '0' && mc <= '9') {
                maxNumChangeCount = Integer.parseInt("" + mc);
            } else {
                maxNumChangeCount = 0;
            }
            maxNumChangeIndex = 0;
        }
    }

    /**
     * number color
     *
     * @return number color
     */
    public int getTextColor() {
        return mNumberColor;
    }

    /**
     * Sets the number color
     *
     * @param numberColor the number color
     */
    public void setTextColor(int numberColor) {
        mNumberColor = numberColor;
        invalidateTextPaintAndMeasurements();
    }

    /**
     * Gets the number size
     *
     * @return the number size
     */
    public float getTextSize() {
        return mTextSize;
    }

    /**
     * Sets the  the number size.
     *
     * @param exampleDimension the number size to use.
     */
    public void setTextSize(float exampleDimension) {
        mTextSize = exampleDimension;
        invalidateTextPaintAndMeasurements();
    }

    private boolean isDefault;
    private String mDefaultCount;

    public void setDefualtCount(int count) {
        mDefaultCount = String.valueOf(count);
        isDefault = true;
        mTextWidth = mTextPaint.measureText(mDefaultCount);
        mCharWidth = mTextWidth / mDefaultCount.length();
        invalidate();
    }

    public void setDefualtCount(long count) {
        mCurShowCount = count;
        mDefaultCount = String.valueOf(count);
        isDefault = true;
        mTextWidth = mTextPaint.measureText(mDefaultCount);
        mCharWidth = mTextWidth / mDefaultCount.length();
        invalidate();
    }

    public void setDefualtCount(float count) {
        mCurShowCount = count;
        mDefaultCount = String.format("%.2f", count);
        isDefault = true;
        mTextWidth = mTextPaint.measureText(mDefaultCount);
        mCharWidth = mTextWidth / mDefaultCount.length();
        invalidate();
    }

    public float getCurShowCount() {
        return mCurShowCount;
    }
}
