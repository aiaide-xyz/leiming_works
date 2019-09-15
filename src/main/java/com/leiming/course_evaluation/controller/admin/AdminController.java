package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.*;
import com.leiming.course_evaluation.repository.ClassRepository;
import com.leiming.course_evaluation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class AdminController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private ClassService classService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private CourseService courseService;
    @RequestMapping("/admin")
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request){
        model.addAttribute("msg", msg);
        if ((Admin)request.getSession().getAttribute("user") == null){
            return new ModelAndView("error/403");
        }
        return new ModelAndView("admin/index");
    }

    @GetMapping("/student")
    @ResponseBody
    public Map<String,Object> student(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Student> content = studentService.findAll(pageable).getContent();
        System.out.println(content);
//        for (Student student:content ) {
//            System.out.println(student.getDepartment().getDptName());
//        }
        Map<String,Object> map = new HashMap<>();
        for (Student s:content
        ) {
            s.setDptName(s.getDepartment().getDptName());
            s.setClassName(s.getCgClass().getClassName());
        }
        map.put("data",content);
        map.put("size",studentService.findAllCount());
        return map;
    }
    @GetMapping("/teacher")
    @ResponseBody
    public Map<String,Object> teacher(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Teacher> content = teacherService.findAll(pageable).getContent();
        System.out.println(content);
//        for (Student student:content ) {
//            System.out.println(student.getDepartment().getDptName());
//        }
        Map<String,Object> map = new HashMap<>();
        for (Teacher s:content
        ) {
            s.setDptName(s.getDepartment().getDptName());
        }
        map.put("data",content);
        map.put("size",studentService.findAllCount());
        return map;
    }
    @GetMapping("/department")
    @ResponseBody
    public Map<String,Object> department(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Department> content = departmentService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",studentService.findAllCount());
        return map;
    }
    @GetMapping("/class")
    @ResponseBody
    public Map<String,Object> class_list(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<CgClass> content = classService.findAll(pageable).getContent();

        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",studentService.findAllCount());
        return map;
    }
    @GetMapping("/test")
    public ModelAndView test(Model model,Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<CgClass> content = classService.findAll(pageable).getContent();
        for (CgClass c:content
             ) {

        }
        System.out.println(content);
        model.addAttribute("class",content);
        return new ModelAndView("admin/test.html","model",model);
    }
    @GetMapping("/course")
    @ResponseBody
    public Map<String,Object> course(Integer page, Integer limit){

        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Course> content = courseService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",courseService.findAllCount());
        return map;
    }

}
