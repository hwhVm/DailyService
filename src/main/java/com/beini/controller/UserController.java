package com.beini.controller;

import com.beini.bean.TokenBean;
import com.beini.bean.UserBean;
import com.beini.http.BaseResponseJson;
import com.beini.http.LoginSuccessResponse;
import com.beini.service.UserService;
import com.beini.util.BLog;
import com.beini.util.IpUtil;
import com.beini.util.RedisCacheUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by beini on 2017/10/20.
 */
@Controller
public class UserController {
    /**
     * 交互流程
     * 客户端通过登录请求提交用户名和密码，服务端验证通过后生成一个Token与该用户进行关联，并将Token返回给客户端。
     * 客户端在接下来的请求中都会携带Token，服务端通过解析Token检查登录状态。
     * 当用户退出登录、其他终端登录同一账号（被顶号）、长时间未进行操作时Token会失效，这时用户需要重新登录。ip变化是否重新登录;
     * <p>
     * 登录请求一定要使用HTTPS，否则无论Token做的安全性多好密码泄露了也是白搭
     * Token的生成方式有很多种，例如比较热门的有JWT（JSON Web Tokens）、OAuth等。
     * <p>
     * 两次ip不一样token失效
     */
    @Autowired
    UserService userService;

    @Autowired
    private RedisCacheUtil redisCacheUtil;

    @Autowired
    IpUtil ipUtil;

    /**
     * login
     * username  or email  login
     *
     * @param userBean
     * @return
     */
    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody UserBean userBean) {
        BLog.d(" login " + userBean.toString());

        String email = userBean.getEmail();
        LoginSuccessResponse baseResponseJson = new LoginSuccessResponse();
        UserBean userBean1 = userService.findUserByEmail(email);
        if (userBean1 != null) {//  is register
            String passwrod = userBean.getPassword();

            //success return token
            List<UserBean> userBeans = userService.queryUserByUserEmailAndPasswrod(email, passwrod);
            BLog.d("          userBeans.size()="+userBeans.size());
            if (userBeans.size() > 0) {
//                TokenBean tokenBean = redisCacheUtil.createToken(userBeans.get(0).getId());
                TokenBean tokenBean = new TokenBean();
                baseResponseJson.setIp(tokenBean.getIp());
                baseResponseJson.setToken(tokenBean.getToken());
                baseResponseJson.setUserId(tokenBean.getUserId());
                baseResponseJson.setReturnCode(0);
            } else {
                baseResponseJson.setReturnCode(1);
                baseResponseJson.setReturnMessage(" error ");
            }
        } else {//faile
            baseResponseJson.setReturnCode(1);
            baseResponseJson.setReturnMessage("user is exist");
        }

        String returnJson = new Gson().toJson(baseResponseJson);
        BLog.d("    returnJson=" + returnJson);
        return returnJson;
    }

    /**
     * logout
     *
     * @param currentUser
     * @return
     */
    @RequestMapping(value = "logout", method = RequestMethod.POST)
    public @ResponseBody
    String logout(@RequestBody UserBean currentUser) {
        redisCacheUtil.deleteToken(currentUser.getUser_id());
        return new Gson().toJson(new BaseResponseJson().setReturnCode(0));
    }

    /**
     * register
     *
     * @param currentUser
     * @return
     */
    @RequestMapping(value = "register", method = RequestMethod.POST)
    public @ResponseBody
    String register(@RequestBody UserBean currentUser) {
        BLog.d(" register " + currentUser.toString());
        BaseResponseJson baseResponseJson = new BaseResponseJson();
        baseResponseJson.setReturnCode(1);
        baseResponseJson.setReturnMessage("email no for null");

        String email = currentUser.getEmail();
        if (StringUtils.isEmpty(email)) {
            return new Gson().toJson(baseResponseJson);
        }

        UserBean userBeans = userService.findUserByEmail(currentUser.getEmail());
        BLog.d("              (userBeans==null)="+(userBeans==null));
        if (userBeans != null) {
            baseResponseJson.setReturnCode(1);
            baseResponseJson.setReturnMessage("user is exist");
        } else {
            int returnSuccess = userService.registerUser(currentUser);
            BLog.d("       returnSuccess=" + returnSuccess);
            baseResponseJson.setReturnCode(0);
            baseResponseJson.setReturnMessage("register is successed");
        }
        return new Gson().toJson(baseResponseJson);
    }

    /**
     * getBackPasswrod
     *
     * @param currentUser
     * @return
     */
    @RequestMapping(value = "getBackPasswrod", method = RequestMethod.POST)
    public @ResponseBody
    String getBackPasswrod(@RequestBody UserBean currentUser) {

        return "";
    }

}
