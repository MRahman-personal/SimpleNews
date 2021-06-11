package com.example.simplenews.Webview;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.simplenews.Database.ArticleEntity;
import com.example.simplenews.Database.DatabaseRepository;

public class OnlineNewsWebViewModel extends AndroidViewModel {

    private final DatabaseRepository databaseRepository;
    private MutableLiveData<String> mutableLiveData;

    public OnlineNewsWebViewModel(Application application){
        super(application);
        databaseRepository = new DatabaseRepository(getApplication());
    }

    public LiveData<String> getUrl() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<String>();
        }
        return mutableLiveData;
    }

    public void addUrl(String url){
        mutableLiveData.setValue(url);
    }

    public void addArticle(ArticleEntity articleEntity){
        databaseRepository.insertArticle(articleEntity);
    }

}
