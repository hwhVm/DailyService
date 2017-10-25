package com.beini.bean;

/**
 * Created by beini on 2017/10/23.
 */
public class TokenBean {

    private String token;

    public void setUserId(int userId) {
        this.userId = userId;
    }

    private int userId;
    private String ip;

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public TokenBean() {

    }

    public TokenBean(int userId, String token) {
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

}
