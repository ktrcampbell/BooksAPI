package com.bigbang.booksapi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.bigbang.booksapi.database.BookDAO;
import com.bigbang.booksapi.database.BookEntity;
import com.bigbang.booksapi.database.BooksDB;
import com.bigbang.booksapi.model.BookResult;
import com.bigbang.booksapi.network.BookRetrofitInstance;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookViewModel extends AndroidViewModel {

    private BookRetrofitInstance bookRetrofitInstance;
    private BookDAO bookDAO;
    private ExecutorService executorService;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookDAO = BooksDB.getInstance(application).bookDAO();
        executorService = Executors.newSingleThreadExecutor();
    }

    public void saveBook(BookEntity newBook){
        executorService.execute(() -> bookDAO.saveBook(newBook));
    }

    public void searchVolumes(String query){
        bookRetrofitInstance.searchBooks(query);
    }

    public Observable<BookResult>getBookList(String query){
        return bookRetrofitInstance
                .searchBooks(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io());
    }
}
