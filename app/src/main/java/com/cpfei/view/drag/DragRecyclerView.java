package com.cpfei.view.drag;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.AttributeSet;

/**
 * Created by cpfei on 2018/5/3.
 */

public class DragRecyclerView extends RecyclerView implements OnItemTouchCallbackListener, OnStartDragListener{

    protected DragRecyclerAdapter dragAdapter;
    protected ItemTouchHelper itemTouchHelper;

    public DragRecyclerView(Context context) {
        this(context, null);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DragRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        MItemTouchHelperCallback mItemTouchHelperCallback = new MItemTouchHelperCallback(this);
        itemTouchHelper = new ItemTouchHelper(mItemTouchHelperCallback);
        itemTouchHelper.attachToRecyclerView(this);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        super.setAdapter(adapter);
        if (adapter instanceof DragRecyclerAdapter) {
            dragAdapter = (DragRecyclerAdapter) adapter;
            dragAdapter.setListener(this);
        }
    }

    @Override
    public void onSwiped(int adapterPosition) {
        if (dragAdapter != null) {
            dragAdapter.onSwiped(adapterPosition);
        }
    }

    @Override
    public boolean onMove(int srcPosition, int targetPosition) {
        if (dragAdapter != null) {
            return dragAdapter.onMove(srcPosition, targetPosition);
        }
        return false;
    }

    @Override
    public void onStartDrag(ViewHolder viewHolder) {
        itemTouchHelper.startDrag(viewHolder);
    }
}
