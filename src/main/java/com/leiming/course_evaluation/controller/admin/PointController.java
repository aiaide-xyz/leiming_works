package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.Course;
import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;
    //教学指标获取数据以及页面
    @GetMapping("/student")
    public String point_student(){
        return "admin/point-student.html";
    }
    @GetMapping("/student/list")
    @ResponseBody
    public Map<String,Object> point_student_list(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Point> content = pointService.findAllByStudent(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",pointService.findAllCountOfStudent());
        return map;
    }
    //增加教学指标页面
    @GetMapping("/addPoint")
    public ModelAndView addPointForStu(Model model,String type){
        model.addAttribute("type",type);
        return new ModelAndView("admin/pointAdd.html","model",model);
    }
    @GetMapping("/editPoint")
    public ModelAndView toEditPoint(Model model,Long id){
        Point point = pointService.finById(id);
        model.addAttribute("point",point);
        return new ModelAndView("admin/pointForm.html","model",model);
    }
    @PostMapping("/editPoint")

    public String editPoint(){
//        try {
//
//        }
        return "ok";
    }
    @PostMapping("/addPoint")
    @ResponseBody
    public String addPoint(Point point){
        try {
            pointService.Save(point);
            return "ok";
        }
        catch (Exception e){
            return String.valueOf(e);
        }
    }






    //系部指标
    @GetMapping("/department")
    public String point_department(){
        return "admin/point-department.html";
    }
    @GetMapping("/department/list")
    @ResponseBody
    public Map<String,Object> point_department_list(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Point> content = pointService.findAllByDepartment(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",pointService.findAllCountOfStudent());
        return map;
    }
}
