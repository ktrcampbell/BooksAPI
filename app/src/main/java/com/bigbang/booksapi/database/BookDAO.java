package com.bigbang.booksapi.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BookDAO {

    @Query("SELECT * FROM FavoriteBook")
    List<FavoriteBook> getFavBooks();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void saveBook(FavoriteBook favoriteBook);
}
