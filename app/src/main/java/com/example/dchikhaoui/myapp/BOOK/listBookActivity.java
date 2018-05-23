package com.example.dchikhaoui.myapp.BOOK;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.example.dchikhaoui.myapp.R;

public class listBookActivity extends AppCompatActivity implements BookContract.View {

    BooksListAdaptater mBookListAdaptater;
    public static TextView MenuCount;
    BookPresenter bookPresenter = new BookPresenter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_book_activity);
        final RecyclerView rv = findViewById(R.id.list_book);
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        mBookListAdaptater = new BooksListAdaptater();
        rv.setAdapter(mBookListAdaptater);
        showBook();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        MenuItem item = menu.findItem(R.id.action_buy);
        item.setActionView(R.layout.actionbar_badge_layout);
        MenuCount = item.getActionView().findViewById(R.id.actionbar_textview);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    public void openDetailed(View view) {
        bookPresenter.mListBooks = mBookListAdaptater.getmListBooks();
        bookPresenter.openListBook(view, MenuCount);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showBook() {
        bookPresenter.LoadBook(mBookListAdaptater);
    }
}
