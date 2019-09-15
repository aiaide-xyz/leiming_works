package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.Admin;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/login")
public class LoginController {
    @RequestMapping(value = "",method = RequestMethod.GET)
    public ModelAndView getLogin(HttpServletRequest request,@ModelAttribute("msg") String msg,Model model){
        if (request.getSession().getAttribute("user")!=null){

            return new ModelAndView("redirect:admin");
        }
        model.addAttribute("msg", msg);
        return new ModelAndView("login.html");
    }

    @RequestMapping(value = "",method = RequestMethod.POST)
    public String postLogin(RedirectAttributes model, HttpServletRequest request, String username, String userCode, String password){
        String code = (String) request.getSession().getAttribute("code");
        System.out.println(code);
        System.out.println(userCode);
        if (code==null){
            model.addFlashAttribute("msg","服务器错误");
            return "redirect:login";
        }
        if (!code.equals(userCode)){
            model.addFlashAttribute("msg","验证码错误！");
            return "redirect:login";

        }
        if (username.equals("123")&&password.equals("123")){

            request.getSession().setAttribute("user",new Admin(username,password));
            model.addFlashAttribute("msg","欢迎"+username+"!");
            return "redirect:admin";

        }
        model.addFlashAttribute("msg","用户名或密码错误");
        return "redirect:login";
    }
}
