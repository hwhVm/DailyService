package com.beini.util;


import com.google.gson.Gson;

/**
 * Created by beini on 2017/10/26.
 */

public class GsonUtil {
    private static Gson gson = null;
    private static GsonUtil gsonUtil = null;

    public static GsonUtil getGsonUtil() {
        if (gsonUtil == null) {
            gsonUtil = new GsonUtil();
            gson = new Gson();
        }
        return gsonUtil;
    }


    public Object fromJson(String str, Class temClass) {
        return gson.fromJson(str, temClass);
    }

    public String toJson(Object object) {
        return Base64Util.encode(gson.toJson(object));
    }
}
