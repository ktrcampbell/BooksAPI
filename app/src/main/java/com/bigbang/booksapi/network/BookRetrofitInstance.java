package com.bigbang.booksapi.network;

import com.bigbang.booksapi.model.BookResult;

import io.reactivex.Observable;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.bigbang.booksapi.util.Constants.*;

public class BookRetrofitInstance {

    private BookService bookService;

    public BookRetrofitInstance() {

        bookService = createService(createRetrofit());
    }

    private Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private BookService createService(Retrofit retrofit) {
        return retrofit.create(BookService.class);
    }

    public Observable<BookResult>searchBooks(String keyword, String author){
        return bookService.searchBooks(keyword, author);
    }

}
