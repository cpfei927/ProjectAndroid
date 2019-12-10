package com.cpfei.project.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cpfei.project.R;

/**
 * @Author: chengpengfei
 * @CreateDate: 2019/11/20 15:13
 * @UpdateUser: 更新者：
 * @UpdateDate: 2019/11/20 15:13
 */
public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.ViewHolder> {

    private Context mCxt;

    public MyRecyclerViewAdapter(Context mCxt) {
        this.mCxt = mCxt;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(mCxt).inflate(R.layout.item_recycler_view, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        switch (position % 4){
            case 0:
                holder.linear.setBackgroundColor(mCxt.getResources().getColor(R.color.color_2bc284));
                break;
            case 1:
                holder.linear.setBackgroundColor(mCxt.getResources().getColor(R.color.color_fc6044));
                break;
            case 2:
                holder.linear.setBackgroundColor(mCxt.getResources().getColor(R.color.color_f6a90e));
                break;
            default:
                holder.linear.setBackgroundColor(mCxt.getResources().getColor(R.color.color_763ecc));
                break;
        }

        holder.itemText.setText("item Count = " + position + "");

    }

    @Override
    public int getItemCount() {
        return 50;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public LinearLayout linear;
        public TextView itemText;
        public ViewHolder(View itemView) {
            super(itemView);
            linear = ((LinearLayout) itemView.findViewById(R.id.itemLinear));
            itemText = ((TextView) itemView.findViewById(R.id.itemText));
        }
    }
}
