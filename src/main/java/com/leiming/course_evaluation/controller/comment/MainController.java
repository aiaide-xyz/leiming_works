package com.leiming.course_evaluation.controller.comment;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
    @RequestMapping("/")
    public String index(){
        return "redirect:login";
    }
}
