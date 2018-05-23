package com.example.dchikhaoui.myapp.DETAILBOOK;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dchikhaoui.myapp.Model.books;
import com.example.dchikhaoui.myapp.R;
import com.example.dchikhaoui.myapp.databinding.ItemBookSelectedAdaptaterBinding;

import java.util.List;

public class BooksListSelectedAdaptater extends RecyclerView.Adapter<BooksListSelectedAdaptater.BookHolderSelected> {
    List<books> mDatas;
    Context context;
    View view;
    DetailBooKPresenter detailBooKPresenter;

    public BooksListSelectedAdaptater(List<books> mDatas, View view) {
        this.mDatas = mDatas;
        this.view = view;
    }


    @Override
    public BookHolderSelected onCreateViewHolder(ViewGroup parent, int viewType) {
        context = parent.getContext();
        ItemBookSelectedAdaptaterBinding viewDataBinding = DataBindingUtil.inflate(LayoutInflater.from
                (parent.getContext()), R.layout.item_book_selected_adaptater, parent, false);

        return new BookHolderSelected(viewDataBinding);
    }

    @Override
    public void onBindViewHolder(final BookHolderSelected holder, int position) {
        detailBooKPresenter = new DetailBooKPresenter(mDatas, this);
        holder.mInflate.setBook(mDatas.get(position));
        holder.mInflate.setBookPresenter(detailBooKPresenter);
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class BookHolderSelected extends RecyclerView.ViewHolder {
        ItemBookSelectedAdaptaterBinding mInflate;

        public BookHolderSelected(ItemBookSelectedAdaptaterBinding inflate) {
            super(inflate.getRoot());
            mInflate = inflate;
        }
    }

    public void pai(View view) {
        detailBooKPresenter.paiAll(view);
    }

}
