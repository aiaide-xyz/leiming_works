package com.leiming.course_evaluation.controller.admin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/page")
public class AdminMainController {
    @RequestMapping("/welcome")
    public String welcome_1(){
        return "admin/welcome.html";
    }
    @RequestMapping("/user-setting")
    public String user_setting(){
        return "admin/user-setting";
    }
    @RequestMapping("/user-password")
    public String user_password(){
        return "admin/user-password.html";
    }
    @RequestMapping("/student/list")
    public String user_list(){
        return "admin/student-list.html";
    }
    @RequestMapping("/student/edit")
    public String user_edit(){
        return "admin/student-edit.html";
    }
    @RequestMapping("/teacher/list")
    public String teacher_list(){
        return "admin/teacher-list.html";
    }
    @RequestMapping("/department/list")
    public String department_list(){
        return "admin/department-list.html";
    }
    @RequestMapping("/class/list")
    public String class_list(){
        return "admin/class-list.html";
    }
    @RequestMapping("/course/list")
    public String course_list(){
        return "admin/course-list.html";
    }
    @RequestMapping("/batch/list")
    public String batch_list(){
        return "admin/batch-list.html";
    }
    @RequestMapping("/teachingManagement/list")
    public String teachingManagement(){
        return "admin/teaching-management-list.html";
    }
}
