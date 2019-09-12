package com.leiming.course_evaluation.controller.comment;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logout")
public class Logout {
    @GetMapping("")
    public String logout(HttpServletRequest request){
        if (request.getSession().getAttribute("user")==null){
            return "请先登录";
        }
        request.getSession().removeAttribute("user");
        return "退出成功！";
    }
}
