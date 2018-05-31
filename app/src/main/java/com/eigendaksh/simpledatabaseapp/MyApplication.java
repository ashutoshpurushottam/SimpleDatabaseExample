package com.eigendaksh.simpledatabaseapp;

import android.app.Application;
import android.arch.persistence.room.Room;
import android.content.Context;
import android.support.v4.app.Fragment;

import com.facebook.stetho.Stetho;

/**
 * Created by Ashutosh Purushottam on 30/05/18.
 * Eigendaksh Development Studio
 * ashu@eigendaksh.com
 */
public class MyApplication extends Application {

    private AppDatabase mDb;

    @Override
    public void onCreate() {
        super.onCreate();
        mDb = Room.databaseBuilder(this,
                AppDatabase.class, "tododb").build();
        Stetho.initializeWithDefaults(this);
    }

    public static AppDatabase getDB(Context context) {
        return ((MyApplication) context.getApplicationContext()).mDb;
    }

    public static AppDatabase getDB(Fragment fragment) {
        return getDB(fragment.getContext());
    }

}
