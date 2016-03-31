package com.example.administrator.myapplication.Utility;

import org.xml.sax.SAXException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

/**
 * Created by Administrator on 2016/3/30.
 * 解析xml文件的类
 */
public class AndroidSaxXml {
    public static List readXml(InputStream inputStream) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser();
        SaxXmlContentHandler handler = new SaxXmlContentHandler();
        saxParser.parse(inputStream, handler);
        inputStream.close();
        return handler.getBooks();
    }
}
