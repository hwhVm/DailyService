package com.beini.bean;

/**
 * Created by beini on 2017/10/23.
 */
public class TokenBean {

    private String token;
    private long userId;

    public TokenBean() {

    }

    public TokenBean(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
