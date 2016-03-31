package com.example.administrator.myapplication.Utility;

import android.content.Context;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by Administrator on 2016/3/29.
 */
public class FileService {
    private Context context;
    public FileService(Context context){
        this.context = context;
    }

    /****
     * 保存文件
     * @param fileName  文件名
     * @param content   追加的内容
     * @return          保存是否成功
     * @throws Exception    FileNotFoundException
     */
    public boolean save(String fileName, String content) throws Exception {
        FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_APPEND);
        fileOutputStream.write(content.getBytes());
        return true;
    }

    /**
     * 读取文件
     * @param fileName  文件名
     * @return          返回文件中的字符
     * @throws Exception    FileNotFindException IOException
     */
    public String read(String fileName) throws Exception {
        FileInputStream fileInputStream = context.openFileInput(fileName);
        ByteArrayOutputStream arrayInputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = fileInputStream.read(buffer)) > 0){
            arrayInputStream.write(buffer, 0, length);
        }
        return arrayInputStream.toString();
    }
}
