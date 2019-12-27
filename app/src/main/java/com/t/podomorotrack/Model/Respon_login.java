package com.t.podomorotrack.Model;

import com.t.podomorotrack.podo.Json_User;

import java.util.List;

public class Respon_login {
    private List<Json_User> data;
    private String msg,sts;

    public Respon_login(List<Json_User> data) {
        this.data = data;
    }

    public List<Json_User> getData() {
        return data;
    }

    public String getMsg() {
        return msg;
    }

    public String getSts() {
        return sts;
    }
}
