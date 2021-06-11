package com.example.simplenews.Api;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("v2/top-headlines")
    Call<StoryResponse> getHeadlineStories(@Query("country") String country);

    @GET("v2/top-headlines")
    Call<StoryResponse> getStories(@Query("country") String country, @Query("category") String category);

}
