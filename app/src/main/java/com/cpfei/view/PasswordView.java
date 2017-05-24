package com.cpfei.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputMethodManager;

import com.cpfei.utils.DensityUtils;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by cpfei on 2017/5/24.
 * 密码输入View 只支持数字
 */

public class PasswordView extends View {


    private int passwordLength = 6;//密码个数
    private long cursorFlashTime = 1;//光标闪动间隔时间 秒
    private int passwordPadding = DensityUtils.dip2px(10);//每个密码间的间隔
    private int passwordSize = DensityUtils.dip2px(40);//单个密码大小
    private int borderColor = Color.BLACK;//边框颜色
    private int borderWidth = DensityUtils.dip2px(2);//下划线粗细
    private int cursorPosition;//光标位置
    private int cursorWidth;//光标粗细
    private int cursorHeight;//光标长度
    private int cursorColor = Color.BLACK;//光标颜色
    private boolean isCursorShowing;//光标是否正在显示
    private boolean isCursorEnable = true;//是否开启光标
    private boolean isInputComplete;//是否输入完毕
    private int cipherTextSize;//密文符号大小
    private boolean cipherEnable = true;//是否开启密文
    private static String CIPHER_TEXT = "*"; //密文符号
    protected Paint paint;
    protected Timer timer;
    protected TimerTask timerTask;

    protected String[] password;
    protected InputMethodManager inputManager;
    protected PasswordListener passwordListener;

    public PasswordView(Context context) {
        this(context, null);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public PasswordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }


    protected void init() {
        paint = new Paint();
        password = new String[passwordLength];
        timerTask = new TimerTask() {
            @Override
            public void run() {
                isCursorShowing = !isCursorShowing;
                postInvalidate();
            }
        };
        timer = new Timer();


        setFocusableInTouchMode(true);
        MyKeyListener MyKeyListener = new MyKeyListener();
        setOnKeyListener(MyKeyListener);
        inputManager = (InputMethodManager) getContext().getSystemService(Context.INPUT_METHOD_SERVICE);
    }


    // 整个View的宽高度计算公式为 ：
    // View宽度 = 单个密码框的宽度 * 密码位数 + 密码框间隔 * （密码位数 - 1）
    // View高度 = 密码框的高度
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int width = 0;
        switch (widthMode) {
            case MeasureSpec.EXACTLY:
                //指定大小，宽度 = 指定的大小
                width = MeasureSpec.getSize(widthMeasureSpec);
                //密码框大小 =  (宽度 - 密码框间距 *(密码位数 - 1)) / 密码位数
                passwordSize = (width - passwordPadding * (passwordLength - 1)) / passwordLength;
                break;
            case MeasureSpec.AT_MOST:
            case MeasureSpec.UNSPECIFIED:
                // 没有指定大小
                width = passwordSize * passwordLength + passwordPadding * (passwordLength - 1);
                break;
        }

        setMeasuredDimension(width, passwordSize);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        //文本大小
        cipherTextSize = passwordSize / 2;
        //光标宽度
        cursorWidth = DensityUtils.dip2px(2);
        //光标长度
        cursorHeight = passwordSize / 2;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // 绘制下划线
        drawUnderLine(canvas, paint);
        //绘制光标
        drawCursor(canvas, paint);
        //绘制密码文本
        drawCipherText(canvas, paint);
    }

    private void drawUnderLine(Canvas canvas, Paint paint) {
        //画笔初始化
        paint.setColor(borderColor);
        paint.setStrokeWidth(borderWidth);
        paint.setStyle(Paint.Style.FILL);
        for (int i = 0; i < passwordLength; i++) {
            //根据密码位数for循环绘制直线
            // 起始点x = paddingLeft + (单个密码框大小 + 密码框边距) * i , 起始点y = paddingTop + 单个密码框大小
            // 终止点x = 起始点x + 单个密码框大小 , 终止点y与起始点一样不变
            canvas.drawLine(getPaddingLeft() + (passwordSize + passwordPadding) * i,
                    getPaddingTop() + passwordSize,
                    getPaddingLeft() + (passwordSize + passwordPadding) * i + passwordSize,
                    getPaddingTop() + passwordSize,
                    paint);
        }
    }


    private void drawCursor(Canvas canvas, Paint paint) {
        paint.setColor(cursorColor);
        paint.setStrokeWidth(cursorWidth);
        paint.setStyle(Paint.Style.FILL);
        //光标未显示 && 开启光标 && 输入位数未满 && 获得焦点
        if (!isCursorShowing && isCursorEnable && !isInputComplete && hasFocus()) {
            // 起始点x = paddingLeft + 单个密码框大小 / 2 + (单个密码框大小 + 密码框间距) * 光标下标
            // 起始点y = paddingTop + (单个密码框大小 - 光标大小) / 2
            // 终止点x = 起始点x
            // 终止点y = 起始点y + 光标高度
            canvas.drawLine((getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * cursorPosition,
                    getPaddingTop() + (passwordSize - cursorHeight) / 2,
                    (getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * cursorPosition,
                    getPaddingTop() + (passwordSize + cursorHeight) / 2,
                    paint);
        }
    }

    private void drawCipherText(Canvas canvas, Paint paint) {
        //画笔初始化
        paint.setColor(Color.GRAY);
        paint.setTextSize(cipherTextSize);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStyle(Paint.Style.FILL);
        //文字居中的处理
        Rect r = new Rect();
        canvas.getClipBounds(r);
        int cHeight = r.height();
        paint.getTextBounds(CIPHER_TEXT, 0, CIPHER_TEXT.length(), r);
        float y = cHeight / 2f + r.height() / 2f - r.bottom;

        //根据输入的密码位数，进行for循环绘制
        for (int i = 0; i < password.length; i++) {
            if (!TextUtils.isEmpty(password[i])) {
                // x = paddingLeft + 单个密码框大小/2 + ( 密码框大小 + 密码框间距 ) * i
                // y = paddingTop + 文字居中所需偏移量
                if (cipherEnable) {
                    //没有开启明文显示，绘制密码密文
                    canvas.drawText(CIPHER_TEXT,
                            (getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * i,
                            getPaddingTop() + y, paint);
                } else {
                    //明文显示，直接绘制密码
                    canvas.drawText(password[i],
                            (getPaddingLeft() + passwordSize / 2) + (passwordSize + passwordPadding) * i,
                            getPaddingTop() + y, paint);
                }
            }
        }
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //cursorFlashTime为光标闪动的间隔时间
        timer.scheduleAtFixedRate(timerTask, 0, cursorFlashTime * 1000);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        timer.cancel();
    }


    @Override
    public InputConnection onCreateInputConnection(EditorInfo outAttrs) {
        return super.onCreateInputConnection(outAttrs);
    }

    /**
     * 密码监听者
     */
    public interface PasswordListener {
        /**
         * 输入/删除监听
         *
         * @param changeText 输入/删除的字符
         */
        void passwordChange(String changeText);

        /**
         * 输入完成
         */
        void passwordComplete();

        /**
         * 确认键后的回调
         *
         * @param password   密码
         * @param isComplete 是否达到要求位数
         */
        void keyEnterPress(String password, boolean isComplete);

    }

    class MyKeyListener implements OnKeyListener {

        @Override
        public boolean onKey(View v, int keyCode, KeyEvent event) {
            int action = event.getAction();
            if (action == KeyEvent.ACTION_DOWN) {
                if (keyCode == KeyEvent.KEYCODE_DEL) {
                    /**
                     * 删除操作
                     */
                    if (TextUtils.isEmpty(password[0])) {
                        return true;
                    }
                    String deleteText = delete();
                    if (passwordListener != null && !TextUtils.isEmpty(deleteText)) {
                        passwordListener.passwordChange(deleteText);
                    }
                    postInvalidate();
                    return true;
                }

                if (keyCode == KeyEvent.KEYCODE_ENTER) {
                    /**
                     * 确认键
                     */
                    if (passwordListener != null) {
                        passwordListener.keyEnterPress(getPassword(), isInputComplete);
                    }
                    return true;
                }

                if (keyCode >= KeyEvent.KEYCODE_0 && keyCode <= KeyEvent.KEYCODE_9) {
                    /**
                     * 只支持数字
                     */
                    if (isInputComplete) {
                        return true;
                    }
                    String addText = add((keyCode - 7) + "");
                    if (passwordListener != null && !TextUtils.isEmpty(addText)) {
                        passwordListener.passwordChange(addText);
                    }
                    postInvalidate();
                    return true;
                }

            }
            return false;
        }
    }

    /**
     * 删除
     */
    private String delete() {
        String deleteText = null;
        if (cursorPosition > 0) {
            deleteText = password[cursorPosition - 1];
            password[cursorPosition - 1] = null;
            cursorPosition--;
        } else if (cursorPosition == 0) {
            deleteText = password[cursorPosition];
            password[cursorPosition] = null;
        }
        isInputComplete = false;
        return deleteText;
    }

    /**
     * 增加
     */
    private String add(String c) {
        String addText = null;
        if (cursorPosition < passwordLength) {
            addText = c;
            password[cursorPosition] = c;
            cursorPosition++;
            if (cursorPosition == passwordLength) {
                isInputComplete = true;
                if (passwordListener != null) {
                    passwordListener.passwordComplete();
                }
            }
        }
        return addText;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            /**
             * 弹出软键盘
             */
            requestFocus();
            inputManager.showSoftInput(this, InputMethodManager.SHOW_FORCED);
            return true;
        }
        return super.onTouchEvent(event);
    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if (!hasWindowFocus) {
            inputManager.hideSoftInputFromWindow(this.getWindowToken(), 0);
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("superState", super.onSaveInstanceState());
        bundle.putStringArray("password", password);
        bundle.putInt("cursorPosition", cursorPosition);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            password = bundle.getStringArray("password");
            cursorPosition = bundle.getInt("cursorPosition");
            state = bundle.getParcelable("superState");
        }
        super.onRestoreInstanceState(state);
    }


    public int getPasswordLength() {
        return passwordLength;
    }

    public void setPasswordLength(int passwordLength) {
        this.passwordLength = passwordLength;
    }

    public long getCursorFlashTime() {
        return cursorFlashTime;
    }

    public void setCursorFlashTime(long cursorFlashTime) {
        this.cursorFlashTime = cursorFlashTime;
    }

    public int getPasswordPadding() {
        return passwordPadding;
    }

    public void setPasswordPadding(int passwordPadding) {
        this.passwordPadding = passwordPadding;
    }

    public int getPasswordSize() {
        return passwordSize;
    }

    public void setPasswordSize(int passwordSize) {
        this.passwordSize = passwordSize;
    }

    public int getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(int borderColor) {
        this.borderColor = borderColor;
    }

    public int getBorderWidth() {
        return borderWidth;
    }

    public void setBorderWidth(int borderWidth) {
        this.borderWidth = borderWidth;
    }

    public int getCursorPosition() {
        return cursorPosition;
    }

    public void setCursorPosition(int cursorPosition) {
        this.cursorPosition = cursorPosition;
    }

    public int getCursorWidth() {
        return cursorWidth;
    }

    public void setCursorWidth(int cursorWidth) {
        this.cursorWidth = cursorWidth;
    }

    public int getCursorHeight() {
        return cursorHeight;
    }

    public void setCursorHeight(int cursorHeight) {
        this.cursorHeight = cursorHeight;
    }

    public int getCursorColor() {
        return cursorColor;
    }

    public void setCursorColor(int cursorColor) {
        this.cursorColor = cursorColor;
    }

    public boolean isCursorShowing() {
        return isCursorShowing;
    }

    public void setCursorShowing(boolean cursorShowing) {
        isCursorShowing = cursorShowing;
    }

    public boolean isCursorEnable() {
        return isCursorEnable;
    }

    public void setCursorEnable(boolean cursorEnable) {
        isCursorEnable = cursorEnable;
    }

    public boolean isInputComplete() {
        return isInputComplete;
    }

    public void setInputComplete(boolean inputComplete) {
        isInputComplete = inputComplete;
    }

    public int getCipherTextSize() {
        return cipherTextSize;
    }

    public void setCipherTextSize(int cipherTextSize) {
        this.cipherTextSize = cipherTextSize;
    }

    public boolean isCipherEnable() {
        return cipherEnable;
    }

    public void setCipherEnable(boolean cipherEnable) {
        this.cipherEnable = cipherEnable;
    }

    public static String getCipherText() {
        return CIPHER_TEXT;
    }

    public static void setCipherText(String cipherText) {
        CIPHER_TEXT = cipherText;
    }


    public String getPassword() {
        String p = "";
        for (int i = 0; i < password.length; i++){
            if (!TextUtils.isEmpty(password[i])) {
                p += password[i];
            }
        }
        if (p.length() == this.password.length) {
            return p;
        }
        return null;
    }

    public InputMethodManager getInputManager() {
        return inputManager;
    }

    public void setInputManager(InputMethodManager inputManager) {
        this.inputManager = inputManager;
    }

    public PasswordListener getPasswordListener() {
        return passwordListener;
    }

    public void setPasswordListener(PasswordListener passwordListener) {
        this.passwordListener = passwordListener;
    }
}
