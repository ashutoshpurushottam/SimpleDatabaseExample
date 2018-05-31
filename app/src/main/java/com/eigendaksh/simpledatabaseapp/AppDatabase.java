package com.eigendaksh.simpledatabaseapp;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;

/**
 * Created by Ashutosh Purushottam on 30/05/18.
 * Eigendaksh Development Studio
 * ashu@eigendaksh.com
 */
@Database(entities = {TodoEntry.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ToDoDao toDoDao();

}
