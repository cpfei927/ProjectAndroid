package com.cpfei.view.drag;

/**
 * Created by cpfei on 2018/5/3.
 */

public interface OnItemTouchCallbackListener {

    /**
     * 当某个Item被滑动删除的时候
     *
     * @param adapterPosition item的position
     */
    void onSwiped(int adapterPosition);

    /**
     * 当两个Item位置互换的时候被回调
     *
     * @param srcPosition    拖拽的item的position
     * @param targetPosition 目的地的Item的position
     * @return 开发者处理了操作应该返回true，开发者没有处理就返回false
     */
    boolean onMove(int srcPosition, int targetPosition);

}
