package com.rojer_ko.homework.lesson_4.data.network;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInit {

    private static final String BASE_URL = "https://api.github.com/";
    private static IRestApiForRepos api;

    public static synchronized IRestApiForRepos newApiInstance() {
        if (api == null) {
            api = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
                    .create(IRestApiForRepos.class);
        }
        return api;
    }
}
