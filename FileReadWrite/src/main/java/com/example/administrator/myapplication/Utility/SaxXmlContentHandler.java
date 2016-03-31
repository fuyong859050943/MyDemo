package com.example.administrator.myapplication.Utility;

import com.example.administrator.myapplication.Entity.Book;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/3/30.
 * SaxXml解析处理器
 */
public class SaxXmlContentHandler extends DefaultHandler {
    private List books;//book集合
    private Book book;//遍历临时book
    private String tagName;//遍历临时节点

    public List getBooks() {
        return books;
    }
    /**
     * 接收文档的开始的通知。
     */
    @Override
    public void startDocument() throws SAXException {
        this.books = new ArrayList<Book>();
    }

    /**
     * 接收字符数据的通知。
     */
    @Override
    public void characters(char[] ch, int start,int length) throws SAXException {
        if (this.tagName != null) {
            String data = new String(ch, start, length);
            if (this.tagName.equals("name")) {
                this.book.setName(data);
            } else if (this.tagName.equals("price")) {
                this.book.setPrice(Float.parseFloat(data));
            }
        }
    }

    /**
     * 接收元素开始的通知。
     * namespaceURI:元素的命名空间
     * localName:元素的本地名称（不带前缀）
     * qName:元素的限定名（带前缀）
     * atts:元素的属性集合
     */
    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (localName.equals("book")) {
            book = new Book();
            book.setId(Integer.parseInt(attributes.getValue(0)));
        }
        this.tagName = localName;
    }

    /**
     * 接收文档的结尾的通知。
     * uri:元素的命名空间
     * localName:元素的本地名称（不带前缀）
     * name:元素的限定名（带前缀）
     */
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (localName.equals("book")) {
            this.books.add(this.book);
        }
        this.tagName = null;
    }
}
