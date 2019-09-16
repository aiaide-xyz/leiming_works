package com.leiming.course_evaluation.controller.teacher;

import com.leiming.course_evaluation.dto.Admin;
import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

@Controller
public class TeacherController {
    @RequestMapping("/teacher")
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request){
        model.addAttribute("msg", msg);
        if (!(request.getSession().getAttribute("user") instanceof Teacher)){
            return new ModelAndView("error/403");
        }
        return new ModelAndView("teacher/index");
    }
}
