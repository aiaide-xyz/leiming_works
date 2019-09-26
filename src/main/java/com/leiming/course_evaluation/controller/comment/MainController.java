package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.service.PointService;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

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
    @RequestMapping("/test")
    public ModelAndView test(Model model){
        Point point = pointService.finById(new Long(1));
        List<TeachingManagement> list = teachingManagementService.findAllList();
        model.addAttribute("point",point);
        model.addAttribute("list",list);
        return new ModelAndView("student/evaluation.html","model",model);

    }
}
