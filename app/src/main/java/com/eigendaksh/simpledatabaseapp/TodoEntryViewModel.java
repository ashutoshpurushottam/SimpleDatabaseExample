package com.eigendaksh.simpledatabaseapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by Ashutosh Purushottam on 11/07/18.
 * Eigendaksh Development Studio
 * ashu@eigendaksh.com
 */
public class TodoEntryViewModel extends AndroidViewModel {

    private final LiveData<List<TodoEntry>> todoList;

    public TodoEntryViewModel(Application application) {
        super(application);
        todoList = MyApplication.getDB(application).toDoDao().getAllToDos();
    }

    public LiveData<List<TodoEntry>> getTodoList() {
        return todoList;
    }

}
