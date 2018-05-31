package com.eigendaksh.simpledatabaseapp;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by Ashutosh Purushottam on 30/05/18.
 * Eigendaksh Development Studio
 * ashu@eigendaksh.com
 */

@Entity(tableName = "todo")
public class TodoEntry {

    @PrimaryKey(autoGenerate = true)
    private long id;

    @ColumnInfo(name = "task_title")
    private String taskTitle;

    @ColumnInfo(name = "task_description")
    private String taskDescription;

    /**
     * Constructor used in other classes to create this object
     */
    @Ignore
    public TodoEntry(String taskTitle, String taskDescription) {
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    public TodoEntry(long id, String taskTitle, String taskDescription) {
        this.id = id;
        this.taskTitle = taskTitle;
        this.taskDescription = taskDescription;
    }

    public long getId() {
        return id;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public String getTaskDescription() {
        return taskDescription;
    }
}
