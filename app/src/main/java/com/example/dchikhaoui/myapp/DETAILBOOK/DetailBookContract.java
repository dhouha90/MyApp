package com.example.dchikhaoui.myapp.DETAILBOOK;

import android.widget.TextView;

import com.example.dchikhaoui.myapp.Model.books;

public interface DetailBookContract {
    interface View {
        void showListDetailBook();

    }

    interface UserActionsListener {


        void increaseQuantityBook(TextView textView, books book);

        void decreaseQuantityBook(TextView textView, books book);

    }

    interface UserActionPai {
        void payBooks();
    }

}
