package com.beini.controller;

import com.beini.util.BLog;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by beini on 2017/11/24.
 */
@Controller
public class Testcontroller {
    @RequestMapping(value = "test1", method = RequestMethod.POST)
    public @ResponseBody
    String test1(HttpSession httpSession) {
        BLog.d(" test1 ------------->ModelAndView");
        httpSession.setAttribute("com.beini", "hhh");
        return "";
    }

    @RequestMapping(value = "test2", method = RequestMethod.POST)
    public void test2(HttpSession httpSession) {
        BLog.d(" test2  ==" + httpSession.getAttribute("com.beini"));
    }

}
