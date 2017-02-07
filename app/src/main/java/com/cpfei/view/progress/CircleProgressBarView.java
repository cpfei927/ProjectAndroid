package com.cpfei.view.progress;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.SweepGradient;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;

import com.cpfei.project.R;
import com.cpfei.utils.DensityUtils;

/**
 * Created by cpfei on 2017/1/13.
 */

public class CircleProgressBarView extends View {


    /*圆弧线宽*/
    private float circleBorderWidth = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 20,
            getResources().getDisplayMetrics());
    /*内边距*/
    private float circlePadding = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 10,
            getResources().getDisplayMetrics());
    /*字体大小*/
    private float textSize = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, 50,
            getResources().getDisplayMetrics());
    /*绘制圆周的画笔*/
    private Paint backCirclePaint;
    /*绘制圆周白色分割线的画笔*/
    private Paint linePaint;
    /*绘制文字的画笔*/
    private Paint textPaint;
    /*百分比*/
    private int percent = 0;
    /*渐变圆周颜色数组*/
    private int[] gradientColorArray = new int[]{Color.GREEN, Color.YELLOW, Color.RED};
    private Paint gradientCirclePaint;


    private Bitmap thumbUp;
    private Bitmap thumbDn;


    private Rect upSrc = new Rect();
    private Rect dnSrc = new Rect();

    private Rect dstRect = new Rect();

    public CircleProgressBarView(Context context) {
        this(context, null);
    }

    public CircleProgressBarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CircleProgressBarView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init() {
        backCirclePaint = new Paint();
        backCirclePaint.setStyle(Paint.Style.STROKE);
        backCirclePaint.setAntiAlias(true);
        backCirclePaint.setColor(Color.LTGRAY);
        backCirclePaint.setStrokeWidth(circleBorderWidth);
//        backCirclePaint.setMaskFilter(new BlurMaskFilter(20, BlurMaskFilter.Blur.OUTER));

        gradientCirclePaint = new Paint();
        gradientCirclePaint.setStyle(Paint.Style.STROKE);
        gradientCirclePaint.setAntiAlias(true);
        gradientCirclePaint.setColor(Color.LTGRAY);
        gradientCirclePaint.setStrokeWidth(circleBorderWidth);

        linePaint = new Paint();
        linePaint.setColor(Color.WHITE);
        linePaint.setStrokeWidth(5);

        textPaint = new Paint();
        textPaint.setAntiAlias(true);
        textPaint.setTextSize(textSize);
        textPaint.setColor(Color.BLACK);


        try {
            thumbUp = BitmapFactory.decodeResource(getResources(), R.mipmap.thumb_up);
            upSrc.set(0, 0, thumbUp.getWidth(), thumbUp.getHeight());

            thumbDn = BitmapFactory.decodeResource(getResources(), R.mipmap.thumb_dn);
            dnSrc.set(0, 0, thumbDn.getWidth(), thumbDn.getHeight());
        } catch (Exception e) {
            e.printStackTrace();
        }

        setBackgroundColor(getResources().getColor(R.color.color_e6e6e6));

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        setMeasuredDimension(Math.min(measureWidth, measureHeight), Math.min(measureWidth, measureHeight));
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

//        int width;
//
//        if(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.EXACTLY)
//        {
//            width = MeasureSpec.getSize(widthMeasureSpec);
//        }
//        else {
//            width = DensityUtils.dip2px(getContext(), 40);
//        }
//
//        int height = width;
//
//        setMeasuredDimension(width, height);
//
//        dstRect.set(width/4, height/4, width/4, height/4);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        RectF rectF = new RectF(circlePadding, circlePadding,
                getMeasuredWidth() - circlePadding, getMeasuredHeight() - circlePadding);

        //1.绘制灰色背景圆环
        canvas.drawArc(rectF, -90, 360, false, backCirclePaint);


        //2.绘制颜色渐变圆环
        //半径
        float radius = getMeasuredWidth() / 2;
        //X轴中点坐标
        float centerX = rectF.centerX();
        float centerY = rectF.centerY();

        SweepGradient sweepGradient = new SweepGradient(radius, radius, gradientColorArray, null);

        //旋转 不然是从0度开始渐变
        Matrix matrix = new Matrix();
        matrix.setRotate(-90, radius, radius);
        sweepGradient.setLocalMatrix(matrix);

        gradientCirclePaint.setShader(sweepGradient);
        gradientCirclePaint.setShadowLayer(10, 10, 10, Color.GREEN);
        canvas.drawArc(rectF, -90, (float) (percent / 100.0) * 360, false, gradientCirclePaint);

//        canvas.drawBitmap(thumbDn, centerX - thumbUp.getWidth()/2, centerY-thumbUp.getHeight()/2, textPaint);


        //3.绘制100份线段，切分空心圆弧
        for (float i = 0; i < 360; i += 3.6) {
            double rad = i * Math.PI / 180;
            float startX = (float) (centerX + (radius - circleBorderWidth) * Math.sin(rad));
            float startY = (float) (centerX + (radius - circleBorderWidth) * Math.cos(rad));

            float stopX = (float) (centerX + radius * Math.sin(rad) + 1);
            float stopY = (float) (centerX + radius * Math.cos(rad) + 1);

            canvas.drawLine(startX, startY, stopX, stopY, linePaint);
        }

        //4.绘制文字
        float textWidth = textPaint.measureText(percent + "%");
        int textHeight = (int) (Math.ceil(textPaint.getFontMetrics().descent -
                textPaint.getFontMetrics().ascent) + 2);
        canvas.drawText(percent + "%", centerX - textWidth / 2, centerX + textHeight / 4, textPaint);

    }

    /**
     * 设置百分比
     *
     * @param percent
     */
    public void setPercent(int percent) {
        if (percent < 0) {
            percent = 0;
        } else if (percent > 100) {
            percent = 100;
        }

        this.percent = percent;
        invalidate();
    }

    public int getPercent() {
        return percent;
    }


}
