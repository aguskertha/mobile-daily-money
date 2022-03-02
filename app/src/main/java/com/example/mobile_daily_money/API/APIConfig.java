package com.example.mobile_daily_money.API;

import androidx.annotation.NonNull;

import com.example.mobile_daily_money.Authentication.TokenManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIConfig {

    public static final String BASE_URL = "https://backend-daily-money.herokuapp.com/api/";
    public static final String BASE_IMAGE_URL = "https://backend-daily-money.herokuapp.com/";
    static OkHttpClient client;
    public static Retrofit retrofit;
    public static APIService getService(TokenManager tokenManager) {
        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY);

        if(tokenManager.isLogin()){
             client = new OkHttpClient.Builder()
                    .addInterceptor(new Interceptor() {
                        @NonNull
                        @Override
                        public Response intercept(@NonNull Chain chain) throws IOException {
                            Request newRequest = chain.request().newBuilder()
                                    .addHeader("authorization",tokenManager.getToken()).build();
                            return chain.proceed(newRequest);
                        }
                    }).build();
        }
        else {
            client = new OkHttpClient.Builder()
                    .addInterceptor(loggingInterceptor)
                    .build();
        }

        Gson gson = new GsonBuilder()
                .setLenient()
                .setDateFormat("yyyy-MM-dd-HH-mm-ss")
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
        return retrofit.create(APIService.class);
    }

}
