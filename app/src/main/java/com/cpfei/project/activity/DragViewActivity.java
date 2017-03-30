package com.cpfei.project.activity;

import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.cpfei.project.R;

public class DragViewActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, DragViewActivity.class);

        return intent;
    }


    private static final String IMAGEVIEW_TAG = "icon bitmap";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_view);
        cragView();
    }


    protected void cragView() {

        final ImageView imageView = (ImageView) findViewById(R.id.cragImage);
        imageView.setTag(IMAGEVIEW_TAG);


        imageView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {

                View.DragShadowBuilder myShadow = new MyDragShadowBuilder(imageView);
                v.startDrag(null,  // the data to be dragged
                        myShadow,  // the drag shadow builder
                        null,      // no need to use local data
                        0          // flags (not currently used, set to 0)
                );
                return false;
            }
        });
    }


    static class MyDragShadowBuilder extends View.DragShadowBuilder{
        private static Drawable shadow;
        public MyDragShadowBuilder(View v) {
            super(v);
            shadow = new ColorDrawable(Color.LTGRAY);
        }


        @Override
        public void onProvideShadowMetrics(Point size, Point touch) {
//            super.onProvideShadowMetrics(outShadowSize, outShadowTouchPoint);
            int width, height;
            width = getView().getWidth() / 2;
            height = getView().getHeight() / 2;
            shadow.setBounds(0, 0, width, height);
            size.set(width, height);
            touch.set(width / 2, height / 2);
        }


        @Override
        public void onDrawShadow(Canvas canvas) {
//            super.onDrawShadow(canvas);
            shadow.draw(canvas);
        }
    }





}
