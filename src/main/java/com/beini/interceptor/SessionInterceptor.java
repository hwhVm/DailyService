package com.beini.interceptor;

import com.beini.constant.NetConstants;
import com.beini.util.BLog;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by beini on 2017/11/21.
 * 主要有两种方式，第一种方式是要定义的Interceptor类要实现了Spring 的HandlerInterceptor 接口，或者是这个类继承实现了HandlerInterceptor 接口的类，
 * 比如Spring 已经提供的实现了HandlerInterceptor 接口的抽象类HandlerInterceptorAdapter ；
 * 第二种方式是实现Spring的WebRequestInterceptor接口，或者是继承实现了WebRequestInterceptor的类。
 * 在一个应用中或者说是在一个请求中可以同时存在多个Interceptor
 */
public class SessionInterceptor implements HandlerInterceptor {
    /**
     * 请求处理之前进行调用
     * false:结束请求
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @return
     * @throws Exception
     */
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o) throws Exception {
//        BLog.d("   this.hashCode()=" + this.hashCode());
//        BLog.d(" ---------------------  " + httpServletRequest.getSession().getId() + "              " + httpServletRequest.getSession().getAttribute(NetConstants.USERID_SESSION));
        //进行处理:例如判定是否登陆
        if (StringUtils.isEmpty(httpServletRequest.getSession().getId()) || StringUtils.isEmpty(httpServletRequest.getSession().getAttribute(NetConstants.USERID_SESSION))) {

        }
        return true;
    }

    /**
     * preHandle 方法的返回值为true 时才能被调用,也就是Controller 方法调用之后执行
     * 但是它会在DispatcherServlet 进行视图返回渲染之前被调用，所以我们可以在这个方法中对Controller 处理之后的ModelAndView 对象进行操作
     * postHandle 方法被调用的方向跟preHandle 是相反的，也就是说先声明的Interceptor 的postHandle 方法反而会后执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
    }

    /**
     * 该方法也是需要当前对应的Interceptor 的preHandle 方法的返回值为true 时才会执行。
     * 该方法将在整个请求结束之后，也就是在DispatcherServlet 渲染了对应的视图之后执行。这个方法的主要作用是用于进行资源清理工作的。
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception
     */
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {
    }
}
