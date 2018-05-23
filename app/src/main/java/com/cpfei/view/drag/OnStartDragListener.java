package com.cpfei.view.drag;

import android.support.v7.widget.RecyclerView;

/**
 * Created by cpfei on 2018/5/3.
 */

public interface OnStartDragListener {

    /**
     * 当View需要拖拽时回调
     *
     * @param viewHolder The holder of view to drag
     */
    void onStartDrag(RecyclerView.ViewHolder viewHolder);


}
