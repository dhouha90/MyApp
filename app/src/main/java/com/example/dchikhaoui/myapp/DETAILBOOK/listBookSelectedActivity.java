package com.example.dchikhaoui.myapp.DETAILBOOK;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dchikhaoui.myapp.BOOK.BookPresenter;
import com.example.dchikhaoui.myapp.Model.books;
import com.example.dchikhaoui.myapp.R;
import com.example.dchikhaoui.myapp.databinding.ListBookActivityBinding;

import java.util.ArrayList;

public class listBookSelectedActivity extends AppCompatActivity implements DetailBookContract.View {
    BooksListSelectedAdaptater mBookSelectedAdaptater;
    View mView;
    TextView mSomme;
    public static ListBookActivityBinding binding;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        showListDetailBook();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.payment_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_payment:
                mView.setVisibility(View.VISIBLE);
                mBookSelectedAdaptater.pai(mView);
                break;
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void showListDetailBook() {
        Bundle arguments = getIntent().getExtras();
        if (arguments != null) {
            ArrayList<books> mListBooks = arguments.getParcelableArrayList(BookPresenter.BOOK_EXTRA);
            setContentView(R.layout.list_book_activity);
            binding = DataBindingUtil.setContentView(this, R.layout.list_book_activity);
            binding.setActivityBook(new PaiBookPresenter());
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            mView = findViewById(R.id.view_res);
            mSomme = findViewById(R.id.all_somme);
            RecyclerView rv = findViewById(R.id.list_book);
            rv.setHasFixedSize(true);
            rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
            mBookSelectedAdaptater = new BooksListSelectedAdaptater(mListBooks, mView);
            rv.setAdapter(mBookSelectedAdaptater);
        }
    }

}


