package com.iucproject.mobilenews.interfaces;

import com.iucproject.mobilenews.articles.NewsAPI;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GETInterface {

    @GET("top-headlines")
    Call<NewsAPI> news ( @Query("country") String country,@Query("category") String category,@Query("q") String query,@Query("apiKey") String apiKey);
    //void getJsonValues(Callback<Article[]> response, @Path("cat") String cat, @Path("api") String api);
}
