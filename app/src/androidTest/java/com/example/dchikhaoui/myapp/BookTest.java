package com.example.dchikhaoui.myapp;

import android.support.test.runner.AndroidJUnit4;

import com.example.dchikhaoui.myapp.utils.UtilsBook;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.HashMap;

import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class BookTest {
    @Test
    public void TestListOffre() {
        HashMap<String, Integer> mHashmap = new HashMap<>();
        mHashmap.put("isbn1", 1);
        mHashmap.put("isbn2", 2);
        mHashmap.put("isbn3", 3);
        UtilsBook utilsBook = new UtilsBook();
        String res = utilsBook.getStringFromArray(mHashmap);
        assertEquals("isbn2,isbn2,isbn1,isbn3,isbn3,isbn3", res);
    }
}
