package com.beini.util;

import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.util.Date;

/**
 * Created by beini on 2017/7/8.
 */
public class BLog {
    private static boolean TAG = true;

    public static void d(String strLog) {
        if (TAG) {
//            Logger.getLogger(BLog.class).debug("  ---------" + DateFormat.getDateTimeInstance().format(new Date()) + "--------------->" + strLog);
            System.out.println("  ---------" + DateFormat.getDateTimeInstance().format(new Date()) + "--------------->" + strLog);
        }
    }

}
