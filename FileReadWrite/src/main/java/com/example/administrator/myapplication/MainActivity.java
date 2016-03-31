package com.example.administrator.myapplication;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.administrator.myapplication.Utility.AndroidSaxXml;
import com.example.administrator.myapplication.Utility.FileService;

import java.io.FileInputStream;
import java.util.List;

public class MainActivity extends Activity {
    FileService fileService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fileService = new FileService(this);
        Button btnRead = (Button) findViewById(R.id.btnRead);
        Button btnWrite = (Button) findViewById(R.id.btnWrite);
        btnRead.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editPath = (EditText) findViewById(R.id.editFilePath);
                EditText editContent = (EditText) findViewById(R.id.editContent);

                String fileDir = getFilesDir().getAbsolutePath();//获取手机临时文件路径
                String cacheDir = getCacheDir().getAbsolutePath();//获取缓存的路径
                String hasSDCard = Environment.getExternalStorageState();//检查设备是否有sdcard
                String SDCard = Environment.getExternalStorageDirectory().getAbsolutePath();//获取sdcard的路径,操作此目录时需要在主配置文件中注册操作权限。

                //String path = getFilesDir() + editPath.getText().toString();
                String path = editPath.getText().toString();
                try{
                    String content = fileService.read(path);
                    editContent.setText(content);
                    Toast.makeText(MainActivity.this, "读取文件成功!", Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "读取文件出错" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editPath = (EditText) findViewById(R.id.editFilePath);
                EditText editContent = (EditText) findViewById(R.id.editContent);
                //String path = getFilesDir() + editPath.getText().toString();
                String path = editPath.getText().toString();
                try{
                    fileService.save(path, editContent.getText().toString());
                    Toast.makeText(MainActivity.this, "写入文件成功!", Toast.LENGTH_LONG).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "写入文件出错" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });

        Button btnReadXml = (Button) findViewById(R.id.btnReadXml);
        btnReadXml.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText editPath = (EditText) findViewById(R.id.editFilePath);
                try {
                    //String path = Environment.getDataDirectory().getAbsolutePath();
                    String path = getFilesDir().getAbsolutePath();
                    FileInputStream fileInputStream = new FileInputStream(path + "/" + editPath.getText().toString());
                    List books = AndroidSaxXml.readXml(fileInputStream);
                    Toast.makeText(MainActivity.this, books.toString(), Toast.LENGTH_SHORT).show();
                }catch (Exception ex){
                    Toast.makeText(MainActivity.this, "解析Xml文件出错" + ex.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
