package com.nouckelson.challengeroomapi.room;

import android.content.Context;

import androidx.room.Room;

public class Connections {
    private static com.nouckelson.challengeroomapi.room.Connections instance;
    private AppDatabase database;
    private  Connections(Context context){
        database= Room.databaseBuilder(context,AppDatabase.class,"db_book")
                .allowMainThreadQueries().build();
    }
    public static com.nouckelson.challengeroomapi.room.Connections getInstance(Context context){
        synchronized (com.nouckelson.challengeroomapi.room.Connections.class){
            if(instance==null){
                instance=new com.nouckelson.challengeroomapi.room.Connections(context);
            }
            return instance;
        }
    }

    public AppDatabase getDatabase(){
        return database;
    }















}
