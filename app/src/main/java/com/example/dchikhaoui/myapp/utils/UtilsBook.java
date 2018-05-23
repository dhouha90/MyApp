package com.example.dchikhaoui.myapp.utils;

import java.util.HashMap;
import java.util.Map;

public class UtilsBook {
    public static String getStringFromArray(HashMap<String, Integer> listBooks) {
        String isbnFinal = "";
        for (Map.Entry mBooks : listBooks.entrySet()) {
            for (int i = 0; i < Integer.valueOf(mBooks.getValue().toString()); i++) {
                isbnFinal = isbnFinal + mBooks.getKey().toString() + ",";
            }
        }
        isbnFinal = isbnFinal.substring(0, isbnFinal.length() - 1);
        return isbnFinal;
    }

}
