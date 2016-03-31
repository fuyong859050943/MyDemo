package com.example.administrator.myapplication;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Administrator on 2016/3/29.
 */
public class SendMesActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.send_main);
        Button sendButton = (Button) findViewById(R.id.sendButton);
        sendButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editPhone = (EditText) findViewById(R.id.editPhone);
                EditText editContent = (EditText) findViewById(R.id.editContent);
                String phoneNumber = editPhone.getText().toString();
                String content = editContent.getText().toString();
                if(content == null && content.trim().length() <= 0)
                    return;
                SmsManager sms = SmsManager.getDefault();
                ArrayList<String> messages = sms.divideMessage(content);
                PendingIntent deliveryIntent = null;

                for(String mes : messages){
                    sms.sendTextMessage(phoneNumber, null, mes, null, deliveryIntent);
                }
                Toast.makeText(SendMesActivity.this, "发送完毕", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
