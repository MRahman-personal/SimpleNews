package com.example.simplenews.Api;

import android.util.Log;
import androidx.lifecycle.MutableLiveData;
import java.io.IOException;
import java.util.List;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class StoryRepository {

    private ApiService service;
    private final CountryCodeProvider countryCodeProvider = new CountryCodeProvider();
    private final String TAG = getClass().getSimpleName();

    public StoryRepository(){
        init();
    }

    public void init(){
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        builder.interceptors().add(interceptor);
        final String BASE_URL = "https://newsapi.org/";

        OkHttpClient client = builder.build();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();

        service = retrofit.create(ApiService.class);
    }

    public MutableLiveData<StoryResponse> requestStories(String type) {
        MutableLiveData<StoryResponse> requestData = new MutableLiveData<>();
        Call<StoryResponse> call = service.getStories(countryCodeProvider.getCountryCode(), type);

        call.enqueue(new Callback<StoryResponse>() {
            @Override
            public void onResponse(Call<StoryResponse> call, Response<StoryResponse> response) {
                assert response.body() != null;
                requestData.setValue(filteredResponse(response.body()));
                Log.i(TAG, "Request Success: " + call.toString());
            }

            @Override
            public void onFailure(Call<StoryResponse> call, Throwable t) {
                Log.e(TAG, "Request failure: " + call.toString());
                t.printStackTrace();
            }
        });
        return requestData;
    }

    public MutableLiveData<StoryResponse> requestHeadlines() {
        MutableLiveData<StoryResponse> headlinesData = new MutableLiveData<>();
        Call<StoryResponse> call = service.getHeadlineStories(countryCodeProvider.getCountryCode());

        call.enqueue(new Callback<StoryResponse>() {
            @Override
            public void onResponse(Call<StoryResponse> call, Response<StoryResponse> response) {
                assert response.body() != null;
                headlinesData.setValue(filteredResponse(response.body()));
                Log.i(TAG, "Request Success: " + call.toString());
            }

            @Override
            public void onFailure(Call<StoryResponse> call, Throwable t) {
                Log.e(TAG, "Request failure: " + call.toString());
                t.printStackTrace();
            }
        });
        return headlinesData;
    }

    public StoryResponse filteredResponse(StoryResponse storyResponse){
        List<Article> iterableResponse = storyResponse.getArticles();
        StoryResponse filteredResponse = new StoryResponse();

        for (Article article : iterableResponse){
            if(article.getTitle() != null && article.getDescription() != null && article.getUrlToImage() != null && article.getUrl() != null){
                filteredResponse.addArticle(article);
            }
        }
        return filteredResponse;
    }

    Interceptor interceptor = new Interceptor() {
        @Override
        public okhttp3.Response intercept(Chain chain) throws IOException {
            final String apiKey = "37929b2227894622b052675acaaf2a98";
            Request newRequest = chain.request().newBuilder().addHeader("X-Api-Key", apiKey).build();
            return chain.proceed(newRequest);
        }
    };

}
