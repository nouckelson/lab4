package com.nouckelson.challengeroomapi.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BookDAO {
    @Insert
    void  insert(Book book);

    @Delete
    void delete (Book book);

    @Query("DELETE FROM Book WHERE isbn =:isbn")
    void delete(long isbn);

    @Update
    void update (Book book);

    @Query("SELECT * FROM Book order by title")
    List<Book> getAllBooks();


    @Query("SELECT * FROM Book WHERE isbn =:isbn")
    Book getBookByISBN(long isbn);



}
