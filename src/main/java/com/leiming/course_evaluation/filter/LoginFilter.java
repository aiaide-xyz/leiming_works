package com.leiming.course_evaluation.filter;


import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//强制登录拦截器
@Component
public class LoginFilter implements HandlerInterceptor {
    /**
     * 完成整个请求之后调用
     */
    @Override
    public void afterCompletion(HttpServletRequest arg0,
                                HttpServletResponse arg1, Object arg2, Exception arg3)
            throws Exception {
    }

    /**
     * 进入controller方法之后，渲染视图之前调用
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response,
                           Object handler, ModelAndView arg3) throws Exception {

    }

    /**
     * 进入controller方法之前调用
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断session有没有“user”的值，也就是有没有登录
        Object user = request.getSession().getAttribute("user");
        if(user!=null){
            return  true;
        }
        //没有值就直接跳转到登录页
        String url = "/login";
        response.sendRedirect(url);
        return false;
    }


}
