package com.cpfei.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.Toast;

/**
 * Created by cpfei on 2018/3/28.
 */

@SuppressLint("AppCompatCustomView")
public class TagEditText extends EditText implements GestureDetector.OnGestureListener{

    private Context context;
    private GestureDetector gesture;

    public TagEditText(Context context) {
        this(context, null);
    }

    public TagEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        gesture = new GestureDetector(context, TagEditText.this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        gesture.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        return false;
    }

    @Override
    public void onLongPress(MotionEvent e) {

        Toast.makeText(context, "Long", Toast.LENGTH_SHORT).show();

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        return false;
    }
}
