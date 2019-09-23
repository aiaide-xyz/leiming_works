package com.leiming.course_evaluation.controller.comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/logout")
//统一退出登录
public class Logout {
    @GetMapping("")
    public String logout(HttpServletRequest request){
        if (request.getSession().getAttribute("user")==null){
            return "redirect:login";
        }
        request.getSession().removeAttribute("user");
        return "redirect:login";
    }
}
