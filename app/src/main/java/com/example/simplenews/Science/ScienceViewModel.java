package com.example.simplenews.Science;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.example.simplenews.Api.StoryRepository;
import com.example.simplenews.Api.StoryResponse;

public class ScienceViewModel extends ViewModel {

    private final StoryRepository storyRepository;
    private MutableLiveData<StoryResponse> mutableLiveData;

    public ScienceViewModel(){
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
        mutableLiveData = storyRepository.requestStories("science");
    }

}
