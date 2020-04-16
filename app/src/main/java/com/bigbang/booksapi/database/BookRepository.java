package com.bigbang.booksapi.database;

import android.content.Context;

import com.bigbang.booksapi.util.DebugLogger;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class BookRepository {

    private CompositeDisposable compDisposable = new CompositeDisposable();
    private BookDAO bookDAO;
    private List<FavoriteBook> favoriteBooks;

    public BookRepository(Context context) {
        BooksDB database = BooksDB.getInstance(context);
        bookDAO = database.bookDAO();
        favoriteBooks = bookDAO.getFavBooks();
    }

    public void saveBook(FavoriteBook favBook){
        Disposable disposable = Completable.fromAction(() -> bookDAO.saveBook(favBook))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> DebugLogger.logDebug("Book added"),
                        throwable -> DebugLogger.logError(throwable));
        compDisposable.add(disposable);
    }

    public void disposeDisposables(){
        compDisposable.dispose();
    }
}
