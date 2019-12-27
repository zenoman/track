package com.t.podomorotrack.Config;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class env {
//    private static final String CACHE_CONTROL = "Cache-Control";
//    public static  final  String ROOT_URL="http://192.168.43.163:8000/";
//    private static final String ROOT_img="http://192.168.43.163:8000/img/";

     private static final String CACHE_CONTROL = "Cache-Control";
     private static  final  String ROOT_URL="http://192.168.137.1:8000/";
     private static final String ROOT_img="http://192.168.137.1:8000/img/";
    //    url
    public  static final  String url_root=ROOT_URL+"api/";
    public Retrofit getRespo(){
        Retrofit retrofit=new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(url_root)
                .build();
        return retrofit;
    }
}
