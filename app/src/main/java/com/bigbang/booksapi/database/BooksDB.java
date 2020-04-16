package com.bigbang.booksapi.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(version = 1, entities = {FavoriteBook.class})
public abstract class BooksDB extends RoomDatabase {
    public abstract BookDAO bookDAO();

    private static BooksDB INSTANCE;

    public static BooksDB getInstance(final Context context) {
        synchronized (BooksDB.class) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        BooksDB.class, "books_db")
                        .allowMainThreadQueries()
                        .build();
            }
            return INSTANCE;
        }
    }
    private static RoomDatabase.Callback bRoomDatabaseCallBack = new RoomDatabase.Callback(){
        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
        }
    };
}
