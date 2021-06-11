package com.example.simplenews.Database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {ArticleEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract ArticleDao articleDao();
}

