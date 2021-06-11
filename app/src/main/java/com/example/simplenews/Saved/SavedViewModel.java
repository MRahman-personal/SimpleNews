package com.example.simplenews.Saved;

import android.app.Application;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.example.simplenews.Database.ArticleEntity;
import com.example.simplenews.Database.DatabaseRepository;
import java.util.List;

public class SavedViewModel extends AndroidViewModel {

    private final DatabaseRepository databaseRepository;
    private MutableLiveData<List<ArticleEntity>> mutableLiveData;

    public SavedViewModel(Application application){
        super(application);
        databaseRepository = new DatabaseRepository(getApplication());
    }

    public LiveData<List<ArticleEntity>> getStories() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<List<ArticleEntity>>();
            loadStories();
        }
        return mutableLiveData;
    }

    public void loadStories(){
        mutableLiveData = databaseRepository.getArticles();
    }

}