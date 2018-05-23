package com.example.dchikhaoui.myapp.BOOK;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;

import com.example.dchikhaoui.myapp.DETAILBOOK.listBookSelectedActivity;
import com.example.dchikhaoui.myapp.Model.books;
import com.example.dchikhaoui.myapp.R;
import com.example.dchikhaoui.myapp.service.HttpResponse;

import java.util.ArrayList;


public class BookPresenter implements BookContract.UserActionsListener {
    ArrayList<books> mListBooks = new ArrayList<>();
    public final static String BOOK_EXTRA = "BOOK_EXTRA";

    public BookPresenter() {
    }

    public BookPresenter(ArrayList<books> mListBooks) {
        this.mListBooks = mListBooks;
    }

    public void LoadBook(BooksListAdaptater mBookListAdaptater) {
        HttpResponse httpResponse = new HttpResponse();
        httpResponse.getListBooks(mBookListAdaptater);
    }

    @Override
    public void openListBook(View view, TextView textView) {
        if (mListBooks.size() == 0) {
            Snackbar snackbar = Snackbar
                    .make(view, R.string.empty_list_product, Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            Intent myIntent = new Intent(view.getContext(), listBookSelectedActivity.class);
            Bundle mBundle = new Bundle();
            mBundle.putParcelableArrayList(BOOK_EXTRA, mListBooks);
            myIntent.putExtras(mBundle);
            mListBooks = new ArrayList<>();
            textView.setText(String.valueOf(mListBooks.size()));
            view.getContext().startActivity(myIntent);
        }
    }

    @Override
    public void AddOrBook(books book, View view) {
        if (mListBooks.contains(book)) {
            mListBooks.remove(book);
            Snackbar snackbar = Snackbar
                    .make(view, R.string.delete_product, Snackbar.LENGTH_LONG);
            snackbar.show();
        } else {
            mListBooks.add(book);
            Snackbar snackbar = Snackbar
                    .make(view, R.string.add_product, Snackbar.LENGTH_LONG);
            snackbar.show();
        }
        listBookActivity.MenuCount.setText(String.valueOf(mListBooks.size()));
    }

    @Override
    public void bind(View view) {
        if (view.getVisibility() == View.VISIBLE) {
            view.setVisibility(View.GONE);
        } else {
            view.setVisibility(View.VISIBLE);
        }
    }

}
