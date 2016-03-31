package com.example.administrator.myapplication.service;

import android.content.Context;
import android.database.Cursor;

import com.example.administrator.myapplication.entity.Contact;
import com.example.administrator.myapplication.tools.MySQLiteHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class ContactsService {
    private MySQLiteHelper sqLiteHelper;

    public ContactsService(Context context){
        sqLiteHelper = new MySQLiteHelper(context);
    }

    /**
     * 保存
     * @param contact   联系人
     */
    public void save(Contact contact){
        String sql = "INSERT INTO contacts (name, phone) VALUES (?, ?)";
        Object[] bindArgs = { contact.getName(), contact.getPhone() };
        this.sqLiteHelper.getWritableDatabase().execSQL(sql, bindArgs);
    }

    /**
     * 通过id查找联系人
     * @param id
     * @return
     */
    public Contact findById(int id){
        String sql = "SELECT _id, name, phone FROM contacts WHERE _id = ?";
        String[] selectionArgs = { String.valueOf(id) };
        Cursor cursor = this.sqLiteHelper.getReadableDatabase().rawQuery(sql, selectionArgs);
        if(cursor.moveToFirst()){
            return new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
        }
        return null;
    }

    /**
     * 更新
     * @param contact
     */
    public void update(Contact contact) {
        String sql = "UPDATE contacts SET name=?, phone=? WHERE _id=?";
        Object[] bindArgs = { contact.getName(), contact.getPhone(), contact.get_id() };
        this.sqLiteHelper.getWritableDatabase().execSQL(sql, bindArgs);
    }

    /**
     * 删除
     * @param id
     */
    public void delete(Integer id) {
        String sql = "DELETE FROM contacts WHERE _id=?";
        Object[] bindArgs = { id };
        this.sqLiteHelper.getReadableDatabase().execSQL(sql, bindArgs);
    }

    /**
     * 获取记录数量
     * @return
     */
    public long getCount() {
        String sql = "SELECT count(*) FROM contacts";
        Cursor cursor = this.sqLiteHelper.getReadableDatabase().rawQuery(sql, null);
        cursor.moveToFirst();
        return cursor.getLong(0);
    }

    /**
     * 获取分页数据
     * @param startIndex
     * @param maxCount
     * @return
     */
    public List getScrollData(long startIndex, long maxCount) {
        String sql = "SELECT _id,name,phone FROM contacts LIMIT ?,?";
        String[] selectionArgs = { String.valueOf(startIndex), String.valueOf(maxCount) };
        Cursor cursor = this.sqLiteHelper.getReadableDatabase().rawQuery(sql, selectionArgs);
        List contacts = new ArrayList();
        while (cursor.moveToNext()) {
            Contact contact = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            contacts.add(contact);
        }
        return contacts;
    }

    /**
     * 获取分页数据，提供给SimpleCursorAdapter使用。
     * @param startIndex
     * @param maxCount
     * @return
     */
    public Cursor getScrollDataCursor(long startIndex, long maxCount) {
        String sql = "SELECT _id,name,phone FROM contacts LIMIT ?,?";
        String[] selectionArgs = { String.valueOf(startIndex), String.valueOf(maxCount) };
        Cursor cursor = this.sqLiteHelper.getReadableDatabase().rawQuery(sql, selectionArgs);
        return cursor;
    }
}
