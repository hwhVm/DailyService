package com.beini.http;

/**
 * Created by beini on 2017/7/8.
 */
public class BaseResponseJson {
    /**
     * ReturnCode : 0
     * ReturnMessage :
     */

    private int ReturnCode;
    private String ReturnMessage;

    public int getReturnCode() {
        return ReturnCode;
    }

    public BaseResponseJson setReturnCode(int ReturnCode) {
        this.ReturnCode = ReturnCode;
        return this;
    }

    public String getReturnMessage() {
        return ReturnMessage;
    }

    public BaseResponseJson setReturnMessage(String ReturnMessage) {
        this.ReturnMessage = ReturnMessage;
        return this;
    }

    @Override
    public String toString() {
        return "BaseResponseJson{" +
                "ReturnCode=" + ReturnCode +
                ", ReturnMessage='" + ReturnMessage + '\'' +
                '}';
    }
}
