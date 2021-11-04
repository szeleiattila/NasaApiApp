package com.attila.szelei.nasaapiapp.service;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class RetrofitClient {
    //Logging HTTP request & Response---> https://www.youtube.com/watch?v=R2c5Pv5cXc0&list=PLrnPJCHvNZuCbuD3xpfKzQWOj3AXybSaM&index=5

    private static String BASE_URL ="http://10.0.2.2:3000";


    private static Retrofit retrofit=null;

    public static Retrofit getRetroFitClient(){

        HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
        loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .build();

        if(retrofit==null){
            retrofit= new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(ScalarsConverterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create())
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .client(okHttpClient)
                    .build();
        }

        return retrofit;
    }

    public static NodeRestService getNodeRestService(){
        NodeRestService nodeRestService = getRetroFitClient().create(NodeRestService.class);
        return nodeRestService;
    }
}
