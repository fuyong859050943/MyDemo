package com.example.administrator.myapplication.tools;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Administrator on 2016/3/30.
 */
public class MySQLiteHelper extends SQLiteOpenHelper {
    public static final String NAME = "contacts";   //数据库名字
    public static final int VERSION = 1;            //数据库版本

    /**
     * CursorFactory指定在执行查询时获得一个游标实例的工厂类。 设置为null，则使用系统默认的工厂类。
     */
    public MySQLiteHelper(Context context) {
        super(context, NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建contacts表，SQL表达式时提供的字段类型和长度仅为提高代码的可读性。
        db.execSQL("CREATE TABLE IF NOT EXISTS contacts("
                + "_id integer primary key autoincrement,"
                + "name varchar(20),"
                + "phone varchar(50))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 仅演示用，所以先删除表然后再创建。
        db.execSQL("DROP TABLE IF EXISTS contacts");
        this.onCreate(db);
    }
}
