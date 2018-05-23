package com.cpfei.view.drag;

import android.support.v7.widget.RecyclerView;
import android.view.MotionEvent;
import android.view.View;

import com.cpfei.project.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Created by cpfei on 2018/5/3.
 */

public abstract class DragRecyclerAdapter<D, H extends RecyclerView.ViewHolder> extends RecyclerView.Adapter<H> {

    protected OnStartDragListener listener;
    protected List<D> mData;

    public void setListener(OnStartDragListener listener) {
        this.listener = listener;
    }

    public void setData(List<D> data) {
        this.mData = data;
        this.notifyDataSetChanged();
    }

    public void addData(List<D> data) {
        if (this.mData == null) {
            this.mData = new ArrayList<>();
        }
        if (data != null) {
            this.mData.addAll(data);
        }
        this.notifyDataSetChanged();
    }

    public List<D> getData() {
        return mData;
    }



    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }

    public void onSwiped(int adapterPosition) {
        // 滑动删除的时候，从数据源移除，并刷新这个Item。
        if (mData != null) {
            mData.remove(adapterPosition);
            notifyItemRemoved(adapterPosition);
        }
    }

    public boolean onMove(int srcPosition, int targetPosition) {
        if (mData != null) {
            // 更换数据源中的数据Item的位置
            Collections.swap(mData, srcPosition, targetPosition);
            // 更新UI中的Item的位置，主要是给用户看到交互效果
            notifyItemMoved(srcPosition, targetPosition);
            return true;
        }
        return false;
    }


    public class DragViewHolder extends RecyclerView.ViewHolder{

        public DragViewHolder(View itemView) {
            super(itemView);
            itemView.findViewById(R.id.drag_handle).setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (listener != null) {
                        listener.onStartDrag(DragViewHolder.this);
                    }
                    return false;
                }
            });
        }
    }

}
