package com.example.yunseung_u.plotchain.application;

import android.app.Application;

import com.example.yunseung_u.plotchain.model.User;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PlotChainApplication extends Application {

    private static Retrofit retrofit = null;
    private static final String TEST_URL = "http://192.168.5.145";
    private static final String BASIC_URL = "http://plotchain.net/";
    private static  User currentUser = new User();


    public static Retrofit getClient(){
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        if (retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(TEST_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }
        return retrofit;
    }

    public static User getCurrentUser() { return currentUser; }

    public static void setCurrentUser(User plotchainuser) { currentUser = plotchainuser;}

}
