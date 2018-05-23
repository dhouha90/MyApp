package com.example.dchikhaoui.myapp.service;

import com.example.dchikhaoui.myapp.Model.AllOffers;
import com.example.dchikhaoui.myapp.Model.books;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface HttpInterface {
    @GET("/books")
    Observable<List<books>> getListBooks();

    @GET("/books/{isbns}/commercialOffers")
    Observable<AllOffers> getOffers(@Path("isbns") String isbns);
}
