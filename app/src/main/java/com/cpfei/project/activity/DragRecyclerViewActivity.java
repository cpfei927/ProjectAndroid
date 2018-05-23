package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;

import com.cpfei.project.R;
import com.cpfei.project.adapter.DragRecyclerViewAdapter;
import com.cpfei.view.drag.DragRecyclerView;

import java.util.ArrayList;

public class DragRecyclerViewActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        return new Intent(context, DragRecyclerViewActivity.class);
    }

    protected ArrayList<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_drag_recycler_view);


        strings = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            strings.add("Item == " + i);
        }

        DragRecyclerView recyclerView = (DragRecyclerView) findViewById(R.id.recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);

        DragRecyclerViewAdapter dragRecyclerViewAdapter = new DragRecyclerViewAdapter();
        recyclerView.setAdapter(dragRecyclerViewAdapter);
        dragRecyclerViewAdapter.setData(strings);
    }
}
