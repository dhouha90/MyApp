package com.example.dchikhaoui.myapp;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

public class BookApplication extends Application {
   // private static WeakReference<BookApplication> mApp;
    private static Context mApp;

    public BookApplication() {
        mApp=this;
    }

    public static Context getApp() {
        return mApp;
    }
}
