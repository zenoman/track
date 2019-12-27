package com.t.podomorotrack.Model;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface iModel {
//    login
    @FormUrlEncoded
    @POST("login")
    Call<Respon_login> login(@Field("user") String user,@Field("pass") String pass);
}
