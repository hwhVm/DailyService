package com.beini.util;

/**
 * Created by beini on 2017/7/8.
 */
public class BLog {
    private static boolean TAG = true;

    public static void d(String strLog) {
        if(TAG){
            System.out.println("--------------->" + strLog);
        }
    }

}
