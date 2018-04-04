package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.view.TagEditText;

public class SelectTextActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, SelectTextActivity.class);

        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_text);


        final EditText editText = (EditText) findViewById(R.id.edit);

        String str = "这是一句话";
        editText.setText(str);
        editText.setSelection(str.length());
        editText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(editText, 0);

        final TagEditText tagEditText = (TagEditText) findViewById(R.id.tagEdit);


        editText.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(SelectTextActivity.this, "onLongClick", Toast.LENGTH_SHORT).show();

                return true;
            }
        });


        findViewById(R.id.btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean enabled = tagEditText.isEnabled();

                if (enabled) {
                    tagEditText.setEnabled(false);
//                    tagEditText.setFocusable(true);
//                    tagEditText.setFocusableInTouchMode(false);
                } else {
                    tagEditText.setEnabled(true);
//                    tagEditText.setFocusable(true);
//                    tagEditText.setFocusableInTouchMode(true);
//                    tagEditText.requestFocus();
                }
                Toast.makeText(SelectTextActivity.this, "enabled = " + enabled, Toast.LENGTH_SHORT).show();
            }
        });







    }
}
