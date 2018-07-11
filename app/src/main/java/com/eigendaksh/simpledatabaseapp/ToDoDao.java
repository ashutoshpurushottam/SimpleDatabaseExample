package com.eigendaksh.simpledatabaseapp;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

/**
 * Created by Ashutosh Purushottam on 30/05/18.
 * Eigendaksh Development Studio
 * ashu@eigendaksh.com
 */
@Dao
public interface ToDoDao {

    @Query("SELECT * FROM todo")
    LiveData<List<TodoEntry>> getAllToDos();

    @Insert
    void insertAll(TodoEntry ... todoEntries);

    @Insert(onConflict = REPLACE)
    long insertToDo(TodoEntry todoEntry);

    @Delete
    void delete(TodoEntry todoEntry);

}
