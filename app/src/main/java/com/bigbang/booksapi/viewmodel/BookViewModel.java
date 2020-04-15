package com.bigbang.booksapi.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.bigbang.booksapi.database.BookDAO;
import com.bigbang.booksapi.database.FavoriteBook;
import com.bigbang.booksapi.database.BookRepository;
import com.bigbang.booksapi.database.BooksDB;
import com.bigbang.booksapi.model.BookItem;
import com.bigbang.booksapi.network.BookRetrofitInstance;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class BookViewModel extends AndroidViewModel {

    private BookRetrofitInstance bookRetrofitInstance = new BookRetrofitInstance();
    private BookDAO bookDAO;
    private BookRepository bookRepository;

    public BookViewModel(@NonNull Application application) {
        super(application);
        bookRepository = new BookRepository(application);
        bookDAO = BooksDB.getInstance(application).bookDAO();
    }

    public void searchVolumes(String query){
        bookRetrofitInstance.searchBooks(query);
    }

    public Observable<List<BookItem>> getBookList(String query){
        return bookRetrofitInstance
                .searchBooks(query)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .map(bookResult -> bookResult.getItems());//transforms List<Item> from BookResult to Item
    }

    public void saveBook(BookItem book){
        FavoriteBook faveBook = new FavoriteBook(book.getId());
        bookRepository.saveBook(faveBook);

    }
}
