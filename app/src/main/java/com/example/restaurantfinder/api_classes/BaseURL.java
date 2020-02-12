package com.example.restaurantfinder.api_classes;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BaseURL {

    public static Retrofit retrofit = null;
    public static Retrofit getRetrofit(){

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
//                    .baseUrl("http://192.168.137.1:5000/")
                    .baseUrl("http://10.1.19.4:5000/")
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }
}