package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.view.listview.BounceListView;
import com.cpfei.view.listview.YLListView;

public class BounceListViewActivity extends AppCompatActivity {


    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, BounceListViewActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bounce_list_view);

        YLListView viewById = (YLListView) findViewById(R.id.bounceListViewv);


        TextView textView = new TextView(this);

        textView.setText("Header View");

        viewById.addHeaderView(textView);
        viewById.addFooterView(textView);
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, android.R.id.text1,
                new String[]{"Item 1", "Item 1", "Item 1", "Item 1", "Item 1", "Item 1",
                        "Item 1", "Item 1", "Item 1", "Item 1", "Item 1", "Item 1",
                        "Item 1", "Item 1", "Item 1", "Item 1", "Item 1",
                        "Item 1", "Item 1", "Item 1", "Item 1", "Item 1", "Item 1", "Item 1",
                        "Item 1", "Item 1", "Item 1", "Item 1"});

        viewById.setAdapter(arrayAdapter);


        Toast.makeText(this, viewById.getFooterViewsCount() + "", Toast.LENGTH_SHORT).show();

    }
}
