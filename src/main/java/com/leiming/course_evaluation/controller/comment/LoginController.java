package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.Admin;
import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import com.leiming.course_evaluation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;
    @RequestMapping("/")
    public String index(){
        return "redirect:login";
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request, @ModelAttribute("msg") String msg, Model model) {
        if (request.getSession().getAttribute("user") != null) {
            if (request.getSession().getAttribute("user") instanceof Admin){
                return new ModelAndView("redirect:admin");
            }else if (request.getSession().getAttribute("user") instanceof Teacher){
                return new ModelAndView("redirect:teacher");
            }else if (request.getSession().getAttribute("user") instanceof Student){
                return new ModelAndView("redirect:student");
            }
        }
        return new ModelAndView("login.html");
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    public String postLogin(RedirectAttributes model, HttpServletRequest request, String username, String userCode, String password) {
        String code = (String) request.getSession().getAttribute("code");
        if (code == null) {
            model.addFlashAttribute("msg", "服务器错误");
            return "redirect:login";
        }
        if (!code.equals(userCode.toLowerCase())) {
            model.addFlashAttribute("msg", "验证码错误！");
            return "redirect:login";

        }
        //判断此对象是否存在
        if (loginService.AdminLogin(username) != null) {
            Admin admin = loginService.AdminLogin(username);
            //如果存在再对比密码
            if (admin.getPassword().equals(password)) {
                //跳转登录成功页面并给session赋值用户
                request.getSession().setAttribute("user", admin);
                model.addFlashAttribute("msg","欢迎"+admin.getUsername()+"管理员!");
                return "redirect:admin";
            } else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }

        } else if (loginService.teacherLogin(username) != null) {
            Teacher teacher = loginService.teacherLogin(username);
            //如果存在则对比密码
            if (teacher.getPassword().equals(password)){
                //跳转登录成功页面并给session赋值用户
                request.getSession().setAttribute("user",teacher);
                model.addFlashAttribute("msg","欢迎"+teacher.getUsername()+"老师!");
                return "redirect:teacher";
            }
            else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }

        } else if (loginService.studentLogin(username) != null){
            Student student = loginService.studentLogin(username);
            //如果学生存在则对比密码
            if (student.getPassword().equals(password)){
                //跳转登录成功页面并给session赋值用户
                request.getSession().setAttribute("user",student);
                model.addFlashAttribute("msg","欢迎"+student.getUsername()+"同学!");
                return "redirect:student";
            }
            else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }
        }
        else {
            model.addFlashAttribute("msg", "此用户不存在");
        }

        return "redirect:login";
    }
}
