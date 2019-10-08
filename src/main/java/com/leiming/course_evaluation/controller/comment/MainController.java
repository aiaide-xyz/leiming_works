package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.service.PointService;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
//强制跳转到登录
public class MainController {
    @Autowired
    private TeachingManagementService teachingManagementService;
    @Autowired
    private PointService pointService;
    //登录页面跳转
    @RequestMapping("/")
    public String index(){
        return "redirect:login";
    }

}
