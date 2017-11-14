package com.cpfei.project.activity;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.cpfei.project.R;
import com.cpfei.view.NumberView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class NumberActivity extends AppCompatActivity {

    public static Intent createIntent(Context context) {
        Intent intent = new Intent(context, NumberActivity.class);

        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_number_activity);

        final NumberView numberView = (NumberView) findViewById(R.id.number);
        final EditText start = (EditText) findViewById(R.id.startNumber);
        final EditText end = (EditText) findViewById(R.id.endNumber);


        findViewById(R.id.sure).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    String startNumber = start.getText().toString();
                    String endNumber = end.getText().toString();

                    if (TextUtils.isEmpty(startNumber) || TextUtils.isEmpty(endNumber)) {
                        return;
                    }
                    if (!isNumeric(startNumber)) {
                        return;
                    }
                    if (!isNumeric(endNumber)) {
                        return;
                    }
                    long startN = Long.parseLong(startNumber);
                    long endN = Long.parseLong(endNumber);
                    if (startN >= endN) {
                        Toast.makeText(NumberActivity.this, "开始数字要小于结束数字",
                                Toast.LENGTH_SHORT).show();
                        return;
                    }

                    numberView.setNumberText(startN, endN);
                    numberView.play();
                }catch (Exception e){
                    Toast.makeText(NumberActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    public boolean isNumeric(String str){
        Pattern pattern = Pattern.compile("[0-9]*");
        Matcher isNum = pattern.matcher(str);
        if(!isNum.matches() ){
            return false;
        }
        return true;
    }

}
