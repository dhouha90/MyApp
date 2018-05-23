package com.example.dchikhaoui.myapp.DETAILBOOK;

import android.view.View;
import android.widget.TextView;

import com.example.dchikhaoui.myapp.Model.books;

import java.util.HashMap;
import java.util.List;

public class DetailBooKPresenter implements DetailBookContract.UserActionsListener {
    int somme = 0;
    HashMap<String, Integer> mIsbn = new HashMap<>();
    List<books> mDatas;
    BooksListSelectedAdaptater mBookSelectedAdaptater;



    public DetailBooKPresenter(List<books> mDatas, BooksListSelectedAdaptater mBookSelectedAdaptater) {
        this.mDatas = mDatas;
        this.mBookSelectedAdaptater = mBookSelectedAdaptater;
        for (int i = 0; i < mDatas.size(); i++) {
            mIsbn.put(mDatas.get(i).getIsbn(), 1);
            somme = somme + mDatas.get(i).getPrice();
        }
    }


    @Override
    public void increaseQuantityBook(TextView textView, books book) {
        int mCount = Integer.parseInt(textView.getText().toString()) + 1;
        textView.setText(String.valueOf(mCount));
        mIsbn.put(book.getIsbn(), mCount);
        somme += book.getPrice();
    }


    @Override
    public void decreaseQuantityBook(TextView textView, books book) {
        int mCount = Integer.parseInt(textView.getText().toString()) - 1;
        somme -= book.getPrice();
        mIsbn.put(book.getIsbn(), mCount);
        if (mCount == 0) {
            mDatas.remove(book);
            mIsbn.remove(book.getIsbn());
            mBookSelectedAdaptater.notifyDataSetChanged();
        } else {
            textView.setText(String.valueOf(mCount));
        }
    }


    public HashMap<String, Integer> getListBooks() {
        return mIsbn;
    }

    public void paiAll(View view) {
        PaiBookPresenter paiBookPresenter = new PaiBookPresenter(view.getContext(), somme, mIsbn);
        paiBookPresenter.PaiAll();
    }


}
