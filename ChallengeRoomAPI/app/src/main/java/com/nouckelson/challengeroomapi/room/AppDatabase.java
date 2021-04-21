package com.nouckelson.challengeroomapi.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {com.nouckelson.challengeroomapi.room.Book.class},version = 1,exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public  abstract com.nouckelson.challengeroomapi.room.BookDAO getBookDAO();
}
