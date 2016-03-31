package com.example.administrator.myapplication;

import android.database.Cursor;
import android.test.AndroidTestCase;
import android.util.Log;

import com.example.administrator.myapplication.entity.Contact;
import com.example.administrator.myapplication.service.ContactsService;
import com.example.administrator.myapplication.tools.MySQLiteHelper;


import org.junit.Test;

import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class ContactsServiceTest extends AndroidTestCase {
    private final static String TAG = "ContactsServiceTest";

    /**
     * 测试生成数据库
     */
    @Test
    public void testCreateTable() {
        MySQLiteHelper sqLiteHelper = new MySQLiteHelper(this.getContext());
        sqLiteHelper.getWritableDatabase();
    }

    /**
     * 测试保存数据
     */
    public void testSave(){
        ContactsService contactsService = new ContactsService(this.getContext());
        Contact contact1 = new Contact(null, "tom", "13898679876");
        Contact contact2 = new Contact(null, "lili", "13041094909");
        Contact contact3 = new Contact(null, "jack", "13504258899");
        Contact contact4 = new Contact(null, "heary", "1335789789");
        contactsService.save(contact1);
        contactsService.save(contact2);
        contactsService.save(contact3);
        contactsService.save(contact4);
    }

    // 测试find
    public void testFind() throws Throwable {
        ContactsService contactsService = new ContactsService(this.getContext());
        Contact contact = contactsService.findById(1);
        Log.i(TAG, contact.toString());
    }

    // 测试update
    public void testUpdate() throws Throwable {
        ContactsService contactsService = new ContactsService(this.getContext());
        Contact contact = contactsService.findById(1);
        contact.setPhone("1399889955");
        contactsService.update(contact);
    }

    // 测试getCount
    public void testGetCount() throws Throwable {
        ContactsService contactsService = new ContactsService(this.getContext());
        Log.i(TAG, contactsService.getCount() + "");
    }

    // 测试getScrollData

    public void testGetScrollData() throws Throwable {
        ContactsService contactsService = new ContactsService(this.getContext());
        List contacts = contactsService.getScrollData(0, 3);
        Log.i(TAG, contacts.toString());
    }

    // 测试getScrollDataCursor
    public void testGetScrollDataCursor() throws Throwable {
        ContactsService contactsService = new ContactsService(this.getContext());
        Cursor cursor = contactsService.getScrollDataCursor(0, 3);
        while (cursor.moveToNext()) {
            Contact contact = new Contact(cursor.getInt(0), cursor.getString(1), cursor.getString(2));
            Log.i(TAG, contact.toString());
        }
    }
}
