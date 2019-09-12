package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.Admin;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request){
        if (request.getSession().getAttribute("user")!=null){
            return new ModelAndView("login-2.html");
        }
        return new ModelAndView("login.html");
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String postLogin(Model model, HttpServletRequest request,String username,String userCode,String password){
        String code = (String) request.getSession().getAttribute("code");
        System.out.println(code);
        System.out.println(userCode);
        if (code==null){
            return "服务器异常";
        }
        if (!code.equals(userCode)){
            return "验证码错误";
        }
        if (username.equals("123")&&password.equals("123")){

            request.getSession().setAttribute("user",new Admin(username,password));
            return "登录成功";
        }
        return "密码或用户名错误";
    }
}
