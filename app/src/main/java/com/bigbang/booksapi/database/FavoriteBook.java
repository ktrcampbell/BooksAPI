package com.bigbang.booksapi.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "FavoriteBook")
public class FavoriteBook {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name="id")
    private String bookId;

    public FavoriteBook(String bookId) {
        this.bookId = bookId;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }
}
