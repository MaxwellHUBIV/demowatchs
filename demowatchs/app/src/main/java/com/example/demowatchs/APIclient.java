package com.example.demowatchs;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIclient {

    private static Retrofit getRetrofit() {
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();//Переменная для передачи данных.
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();

        Retrofit retrofit = new Retrofit.Builder()//Данные, которые
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://cinema.areas.su/")//Адрес сервера
                .client(okHttpClient)
                .build();
        return retrofit;
    }

    public static UserService getUserService(){//Публичный класс получения данных
        UserService userService = getRetrofit().create(UserService.class);
        return  userService;
    }
}