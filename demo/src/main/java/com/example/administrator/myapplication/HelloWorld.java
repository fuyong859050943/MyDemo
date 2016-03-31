package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 * Created by Administrator on 2016/3/29.
 */
public class HelloWorld extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_layout);
        TextView labelText = (TextView) findViewById(R.id.textView);
        labelText.setText("你好,新设置的值", TextView.BufferType.EDITABLE);
    }
}
