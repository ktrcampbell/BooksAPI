package com.bigbang.booksapi.network;

import com.bigbang.booksapi.model.BookResult;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface BookService {

    @GET("/books/v1/volumes")
    Observable<BookResult>searchBooks(@Query("q") String query, @Query("inauthor")String author);
}
