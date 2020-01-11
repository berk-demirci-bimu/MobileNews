package com.iucproject.mobilenews.api;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    public static final String BASE_URL = "https://newsapi.org/v2/";
    private static Retrofit retrofit = null;
    public static Retrofit getInstance() {
        if (retrofit==null) {
            retrofit = new Retrofit.Builder().client(GetClient())
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
    public static OkHttpClient GetClient(){
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        return  httpClient.addInterceptor(logging).build();
    }
}
