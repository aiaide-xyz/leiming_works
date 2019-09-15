package com.leiming.course_evaluation.controller.comment;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/logout")
public class Logout {

    @GetMapping("")
    public String logout(HttpServletRequest request){
        if (request.getSession().getAttribute("user")==null){
            return "请先登录";
        }
        request.getSession().removeAttribute("user");
        return "退出成功";
    }
}
