package com.t.podomorotrack.Config;

import android.content.Context;
import android.content.SharedPreferences;

import com.t.podomorotrack.podo.Json_User;

public class Spf {
    private final String prefname="pref_track";
    private  final String key_id="key_id";
    private  final String key_nip="key_nip";
    private final String key_name="key_nama";
    private  final String key_usm="key_usm";
    private final String key_telp="key_telp";
    private final String key_alamat="key_alamat";
    private final String key_token="key_token";
    private final String key_foto="key_foto";

    private static  Spf mInstance;
    private static Context mContext;
    private Spf(Context context){mContext=context;}
    public static synchronized Spf getmInstance(Context context){
        if(mInstance==null){
            mInstance=new Spf(context);
        }
        return mInstance;
    }
    public void userLogin(Json_User user){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(prefname,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.putString(key_id,user.getId());
        editor.putString(key_nip,user.getNip());
        editor.putString(key_name,user.getNama());
        editor.putString(key_usm,user.getUsername());
        editor.putString(key_telp,user.getTelp());
        editor.putString(key_alamat,user.getAlamat());
        editor.putString(key_token,user.getApi_token());
        editor.putString(key_foto,user.getFoto());
        editor.apply();
    }
    public boolean isLogIn(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(prefname,Context.MODE_PRIVATE);
        return sharedPreferences.getString(key_usm,null)!=null;
    }
    public  void logout(){
        SharedPreferences sharedPreferences=mContext.getSharedPreferences(prefname,Context.MODE_PRIVATE);
        SharedPreferences.Editor editor=sharedPreferences.edit();
        editor.clear();
        editor.apply();
    }
    public Json_User getUser(){
        SharedPreferences sp=mContext.getSharedPreferences(prefname,Context.MODE_PRIVATE);
        return new Json_User(
                sp.getString(key_id,null),
                sp.getString(key_nip,null),
                sp.getString(key_name,null),
                sp.getString(key_usm,null),
                sp.getString(key_telp,null),
                sp.getString(key_alamat,null),
                sp.getString(key_token,null),
                sp.getString(key_foto,null)
        );
    }
}
