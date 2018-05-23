package com.cpfei.project.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cpfei.project.R;
import com.cpfei.view.drag.DragRecyclerAdapter;

import java.util.List;

/**
 * Created by cpfei on 2018/5/3.
 */

public class DragRecyclerViewAdapter extends DragRecyclerAdapter<String, DragRecyclerViewAdapter.ViewHolder> {

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_drag_hand, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.mTextView.setText(mData.get(position));
    }

    class ViewHolder extends DragRecyclerAdapter.DragViewHolder {
        public TextView mTextView;
        public ViewHolder(View itemView) {
            super(itemView);
            mTextView = (TextView) itemView.findViewById(R.id.title);
        }
    }

}
