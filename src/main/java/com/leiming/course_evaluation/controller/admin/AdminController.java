package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.*;
import com.leiming.course_evaluation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
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
    @Autowired
    private BatchService batchService;
    @Autowired
    private TeachingManagementService teachingManagementService;
    //管理员登录后主页面
    @RequestMapping(value = "/admin",method = RequestMethod.GET)
    public ModelAndView index(@ModelAttribute("msg") String msg, Model model, HttpServletRequest request){
        model.addAttribute("msg", msg);
//        if ((Admin)request.getSession().getAttribute("user") == null){
        if (!(request.getSession().getAttribute("user") instanceof Admin)){
            return new ModelAndView("error/403");
        }
        return new ModelAndView("admin/index");
    }




    //修改账号（信息，密码等）
    @PostMapping("/admin")
    @ResponseBody
    public String modifyAdmin(Admin admin){
        return "ok";
    }














    //学生列表获取
    @GetMapping("/students")
    @ResponseBody
    public Map<String,Object> student(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Student> content = studentService.findAll(pageable).getContent();
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
    //教师列表获取
    @GetMapping("/teachers")
    @ResponseBody
    public Map<String,Object> teacher(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Teacher> content = teacherService.findAll(pageable).getContent();
        System.out.println(content);
        Map<String,Object> map = new HashMap<>();
        for (Teacher s:content
        ) {
            s.setDptName(s.getDepartment().getDptName());
        }
        map.put("data",content);
        map.put("size",teacherService.findAllCount());
        return map;
    }
    //院系列表获取
    @GetMapping("/departments")
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
    //班级列表获取
    @GetMapping("/classes")
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
    //课程列表获取
    @GetMapping("/courses")
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
    //【批次】（年级）列表获取
    @GetMapping("/batches")
    @ResponseBody
    public Map<String,Object> batches(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Batch> content = batchService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",batchService.findAllCount());
        return map;
    }
    //授课管理（包括年级，批次）
    @GetMapping("/teachingManagements")
    @ResponseBody
    public Map<String,Object> teachingManagements(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<TeachingManagement> content = teachingManagementService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",teachingManagementService.findAllCount());
        return map;
    }


}
