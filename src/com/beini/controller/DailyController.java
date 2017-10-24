package com.beini.controller;

import com.beini.bean.DailyBean;
import com.beini.constant.NetConstants;
import com.beini.http.BaseResponseJson;
import com.beini.service.DailyService;
import com.beini.util.BLog;
import com.google.gson.Gson;
import javafx.scene.input.DataFormat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * Created by beini on 2017/7/8.
 */
@Controller
public class DailyController {
    @Autowired
    DailyService dailyService;

//    @RequestMapping(value = "addDaily", method = RequestMethod.POST)
//    public void addDaily(@RequestBody DailyBean dailyBean, HttpServletResponse response, PrintWriter out) {
//        BLog.d("              addDaily     " + dailyBean.toString());
//        BaseResponseJson responseJson = new BaseResponseJson();
//        try {
//            int isSuccess = userService.insertDaily(dailyBean);
////            BLog.d("  isSuccess=" + isSuccess);
//            if (isSuccess == 1) {
//                responseJson.setReturnCode(NetConstants.IS_SUCCESS);
//            } else {
//                responseJson.setReturnCode(NetConstants.IS_FAILED);
//            }
//        } catch (Exception e) {
//            responseJson.setReturnCode(NetConstants.IS_FAILED);
//        }
//        response.setContentType("text/htm;charset=utf-8");
//        response.setHeader("pragma", " no-cache");
//        response.setHeader("cache-control", "no-cache");
//        out.write(new Gson().toJson(responseJson));
//    }

    @RequestMapping(value = "addDaily", method = RequestMethod.POST)
    public @ResponseBody
    String addDaily(@RequestBody DailyBean dailyBean) {
        BLog.d("    addDaily   "+ new Date().toLocaleString());
        BaseResponseJson responseJson = new BaseResponseJson();
        try {
            int isSuccess = dailyService.insertDaily(dailyBean);
            if (isSuccess == 1) {
                responseJson.setReturnCode(NetConstants.IS_SUCCESS);
            } else {
                responseJson.setReturnCode(NetConstants.IS_FAILED);
            }
        } catch (Exception e) {
            responseJson.setReturnCode(NetConstants.IS_FAILED);
        }
        return new Gson().toJson(responseJson);
    }

    @RequestMapping(value = "test", method = RequestMethod.GET)
    public void test() {
        BLog.d("  test  ");
    }

}