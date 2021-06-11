package com.example.simplenews.Database;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Room;
import java.util.List;

public class DatabaseRepository {

    private final ArticleDao articleDao;
    private final MutableLiveData<List<ArticleEntity>> articles = new MutableLiveData<>();

    public DatabaseRepository(Context context){
        AppDatabase db = Room.databaseBuilder(context, AppDatabase.class, "article_db").allowMainThreadQueries().build();
        articleDao = db.articleDao();
    }

    public MutableLiveData<List<ArticleEntity>> getArticles(){
        articles.setValue(articleDao.getAll());
        return articles;
    }

    public void insertArticle(ArticleEntity article){
        articleDao.insertArticle(article);
    }

    public void deleteArticle(String url){
        articleDao.deleteByUrl(url);
    }

}
