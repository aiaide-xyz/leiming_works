package com.leiming.course_evaluation.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/login")
    public String index(){
        return "login.html";
    }
    @RequestMapping("/toRegister")
    public String toRegister(){
        return "register.html";
    }
}
