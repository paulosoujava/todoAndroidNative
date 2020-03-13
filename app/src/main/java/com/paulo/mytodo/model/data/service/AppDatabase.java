package com.paulo.mytodo.model.data.service;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.paulo.mytodo.model.entity.ItemTitle;
import com.paulo.mytodo.model.entity.Title;

@Database(entities = {
        Title.class,
        ItemTitle.class
}, version = 1)
public abstract class AppDatabase extends RoomDatabase {


    private static final String DB_NAME = "todo.db";
    private static volatile AppDatabase instance;

    static synchronized AppDatabase getInstance(Context context) {
        if (instance == null) {
            instance = create(context);
        }
        return instance;
    }


    private static AppDatabase create(final Context context) {
        return Room.databaseBuilder(
                context,
                AppDatabase.class,
                DB_NAME)
                .allowMainThreadQueries() // SHOULD NOT BE USED IN PRODUCTION !!!
                .build();
    }

    public abstract TitleDAO titleDAO();

    public abstract ItemTitleDAO itemTitleDAO();

}
