package com.example.dchikhaoui.myapp.BOOK;

import android.widget.TextView;

import com.example.dchikhaoui.myapp.Model.books;

public interface BookContract {

    interface View {
        void showBook();
    }

    interface UserActionsListener {
        void openListBook(android.view.View view, TextView textView);

        void AddOrBook(books book, android.view.View view);

        void bind(android.view.View view);

    }
}
