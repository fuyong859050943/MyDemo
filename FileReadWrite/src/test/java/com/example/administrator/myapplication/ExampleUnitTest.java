package com.example.administrator.myapplication;

import android.os.Environment;
import android.util.Log;

import com.example.administrator.myapplication.Utility.AndroidSaxXml;
import org.junit.Test;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.List;
import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() throws Exception {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void saxXmlTest(){
        try {
            InputStream fileInputStream = this.getClass().getClassLoader().getResourceAsStream("books.xml");
            //String path = Environment.getRootDirectory().getAbsolutePath();
            //FileInputStream fileInputStream = new FileInputStream(path + "books.xml");
            List books = AndroidSaxXml.readXml(fileInputStream);
            Log.i("AndroidSamXml", "saxXmlTest:" + books.toString());
        }catch (Exception ex){
            Log.e("AndroidSamXml", "saxXmlTest: " + ex.getMessage());
        }

    }
}