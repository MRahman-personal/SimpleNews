package com.example.simplenews.Health;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.simplenews.Api.StoryRepository;
import com.example.simplenews.Api.StoryResponse;

public class HealthViewModel extends ViewModel {

    private final StoryRepository storyRepository;
    private MutableLiveData<StoryResponse> mutableLiveData;

    public HealthViewModel(){
        storyRepository = new StoryRepository();
    }

    public LiveData<StoryResponse> getStories() {
        if (mutableLiveData == null) {
            mutableLiveData = new MutableLiveData<StoryResponse>();
            loadStories();
        }
        return mutableLiveData;
    }

    public void loadStories() {
        mutableLiveData = storyRepository.requestStories("health");
    }

}
