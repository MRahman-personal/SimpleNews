package com.example.simplenews.Database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;

@Dao
public interface ArticleDao {

    @Query("SELECT * FROM articleentity")
    List<ArticleEntity> getAll();

    @Insert
    void insertArticle(ArticleEntity article);

    @Query("DELETE FROM articleentity WHERE url_string = :url")
    void deleteByUrl(String url);

}

