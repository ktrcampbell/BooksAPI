package com.bigbang.booksapi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bigbang.booksapi.model.BookResult;
import com.bigbang.booksapi.network.BookRetrofitInstance;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookViewModel extends AndroidViewModel {

    private BookRetrofitInstance bookRetrofitInstance;

    public BookViewModel(@NonNull Application application) {
        super(application);
    }

    public void searchVolumes(String keyword, String author){
        bookRetrofitInstance.searchBooks(keyword, author);
    }
    public Observable<BookResult>getList(String keyword, String author){
        return bookRetrofitInstance
                .searchBooks(keyword, author)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
