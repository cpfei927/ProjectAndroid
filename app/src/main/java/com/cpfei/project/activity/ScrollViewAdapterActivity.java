package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.cpfei.project.R;
import com.cpfei.project.adapter.MyRecyclerViewAdapter;

public class ScrollViewAdapterActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, ScrollViewAdapterActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scroll_view_adapter);

        LinearLayout llNestedView = findViewById(R.id.llNestedView);


        MyRecyclerViewAdapter myRecyclerViewAdapter = new MyRecyclerViewAdapter(this);

        for (int i = 0; i < myRecyclerViewAdapter.getItemCount(); i++) {

            MyRecyclerViewAdapter.ViewHolder viewHolder =
                    myRecyclerViewAdapter.onCreateViewHolder(llNestedView,
                            myRecyclerViewAdapter.getItemViewType(i));
            myRecyclerViewAdapter.onBindViewHolder(viewHolder, i);
            llNestedView.addView(viewHolder.itemView);
        }



    }
}
