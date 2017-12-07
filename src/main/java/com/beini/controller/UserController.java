package com.beini.controller;

import com.beini.bean.TokenBean;
import com.beini.bean.UserBean;
import com.beini.constant.NetConstants;
import com.beini.http.BaseResponseJson;
import com.beini.http.LoginResponse;
import com.beini.service.UserService;
import com.beini.util.BLog;
import com.beini.util.IpUtil;
import com.beini.util.RedisCacheUtil;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

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
     * login
     * username  or email  login
     *
     * @param userBean
     * @return
     */

    @RequestMapping(value = "login", method = RequestMethod.POST)
    public @ResponseBody
    String login(@RequestBody UserBean userBean, HttpSession httpSession) {
        BLog.d(" login " + userBean.toString() + "     " + httpSession.getId() + "   context path  =" + httpSession.getServletContext().getContextPath());
        String email = userBean.getEmail();
        LoginResponse loginResponse = new LoginResponse();
        UserBean userBean1 = userService.findUserByEmail(email);
        if (userBean1 != null) {//  is register   Optional.of(userBean).isPresent();
            String passwrod = userBean.getPassword();
            //success return token
            List<UserBean> userBeans = userService.queryUserByUserEmailAndPasswrod(email, passwrod);
            if (userBeans.size() > 0) {
//              TokenBean tokenBean = redisCacheUtil.createToken(userBeans.get(0).getUser_id());
                //session缓存
                httpSession.setAttribute(NetConstants.USERID_SESSION, userBeans.get(0).getUser_id());
                loginResponse.setUserBean(userBeans.get(0));
                loginResponse.setReturnCode(NetConstants.IS_SUCCESS);
                loginResponse.setReturnMessage("success");
            } else {
                loginResponse.setReturnCode(NetConstants.IS_FAILED);
                loginResponse.setReturnMessage(" error ");
            }
        } else {//faile
            loginResponse.setReturnCode(NetConstants.IS_FAILED);
            loginResponse.setReturnMessage("user is exist");
        }

        String returnJson = new Gson().toJson(loginResponse);
        BLog.d("    returnJson=" + returnJson);

        return returnJson;
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

        String email = currentUser.getEmail();
        if (StringUtils.isEmpty(email)) {
            baseResponseJson.setReturnCode(NetConstants.IS_FAILED);
            baseResponseJson.setReturnMessage("email no for null");
            return new Gson().toJson(baseResponseJson);
        }

        UserBean userBeans = userService.findUserByEmail(currentUser.getEmail());
        BLog.d("              (userBeans==null)=" + (userBeans == null));
        if (userBeans != null) {
            baseResponseJson.setReturnCode(NetConstants.IS_FAILED);
            baseResponseJson.setReturnMessage("user is exist");
        } else {
            int returnSuccess = userService.registerUser(currentUser);
            BLog.d("       returnSuccess=" + returnSuccess);
            baseResponseJson.setReturnCode(NetConstants.IS_SUCCESS);
            baseResponseJson.setReturnMessage("register is successed");
        }
        return new Gson().toJson(baseResponseJson);
    }

    /**
     * getBackPasswrod
     *  internal
     * @param currentUser
     * @return
     */
    @RequestMapping(value = "getBackPasswrod", method = RequestMethod.POST)
    public @ResponseBody
    String getBackPasswrod(@RequestBody UserBean currentUser) {

        return "";
    }

}
