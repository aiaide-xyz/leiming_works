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

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request, @ModelAttribute("msg") String msg, Model model) {
        if (request.getSession().getAttribute("user") != null) {

            return new ModelAndView("redirect:admin");
        }
        model.addAttribute("msg", msg);
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
//        if (username.equals("123")&&password.equals("123")){
        //拿到管理员对象
//        Admin admin = loginService.AdminLogin(username);
//        System.out.println(admin.getUsername());
//        System.out.println(admin.getPassword());
//
//        //拿到教师对象
//        Teacher teacher = loginService.teacherLogin(username);
//
//        //拿到学生对象
//        Student student = loginService.studentLogin(username);
        //判断此对象是否存在
        if (loginService.AdminLogin(username) != null) {
            Admin admin = loginService.AdminLogin(username);
            //如果存在再对比密码
            if (admin.getPassword().equals(password)) {
                //跳转
                request.getSession().setAttribute("user", new Admin(username, password));
//            model.addFlashAttribute("msg","欢迎"+username+"!");
                return "redirect:admin";
            } else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }

        } else if (loginService.teacherLogin(username) != null) {
            Teacher teacher = loginService.teacherLogin(username);
            //如果存在则对比密码
            if (teacher.getPassword().equals(password)){
                //跳转
                Teacher teacherTemp = new Teacher();
                teacherTemp.setTeacherNumber(username);
                teacherTemp.setPassword(password);
                request.getSession().setAttribute("user",teacherTemp);
                return "redirect:teacher";
            }
            else {
                model.addFlashAttribute("msg", "用户名或密码错误");
            }

        } else if (loginService.studentLogin(username) != null){
            Student student = loginService.studentLogin(username);
            //如果学生存在则对比密码
            if (student.getPassword().equals(password)){
                //声明对象
                Student studentTemp = new Student();
                studentTemp.setStuNumber(username);
                studentTemp.setPassword(password);

                //跳转
                request.getSession().setAttribute("user",studentTemp);

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
