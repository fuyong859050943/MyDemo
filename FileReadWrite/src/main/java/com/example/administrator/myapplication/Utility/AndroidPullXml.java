package com.example.administrator.myapplication.Utility;

import android.util.Xml;

import com.example.administrator.myapplication.Entity.Book;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;
import org.xmlpull.v1.XmlSerializer;

import java.io.InputStream;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 */
public class AndroidPullXml {
    public static List readXML(InputStream inputStream, String inputEncoding) throws Exception {
        // 创建Pull解析
        XmlPullParserFactory pullParserFactory = XmlPullParserFactory.newInstance();
        XmlPullParser pullParser = pullParserFactory.newPullParser();
        // 解析XML
        pullParser.setInput(inputStream, inputEncoding);
        // 开始
        int eventType = pullParser.getEventType();
        List books = null;
        Book book = null;
        while (eventType != XmlPullParser.END_DOCUMENT) {
            String nodeName = pullParser.getName();
            switch (eventType) {
                // 文档开始
                case XmlPullParser.START_DOCUMENT:
                    books = new ArrayList();
                    break;
                // 节点开始
                case XmlPullParser.START_TAG:
                    if ("book".equals(nodeName)) {
                        book = new Book();
                        book.setId(Integer.parseInt(pullParser.getAttributeValue(0)));
                    } else if ("name".equals(nodeName)) {
                        book.setName(pullParser.nextText());
                    } else if ("price".equals(nodeName)) {
                        book.setPrice(Float.parseFloat(pullParser.nextText()));
                    }
                    break;
                // 节点结束
                case XmlPullParser.END_TAG:
                    if ("book".equals(nodeName)) {
                        books.add(book);
                        book = null;
                    }
                    break;
            }
            eventType = pullParser.next();
        }
        return books;
    }

    public static void writeXML(Writer writer, List<Book> books) throws Exception {
        // 创建XML生成器
        XmlSerializer writexml = Xml.newSerializer();
        writexml.setOutput(writer);
        // 生成XML文档
        writexml.startDocument("UTF-8", true);
        writexml.startTag("", "books");
        for (Book book : books) {
            // name
            writexml.startTag("", "name");
            writexml.attribute("", "id", book.getId() + "");
            writexml.text(book.getName());
            writexml.endTag("", "name");
            // price
            writexml.startTag("", "price");
            writexml.text(book.getPrice() + "");
            writexml.endTag("", "price");
        }
        //
        writexml.endTag("", "books");
    }

}
