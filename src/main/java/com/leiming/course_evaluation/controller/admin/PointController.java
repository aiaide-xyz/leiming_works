package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.Course;
import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/point")
public class PointController {
    @Autowired
    private PointService pointService;
    //学生指标
    @GetMapping("/student")
    public String point_student(){
        return "admin/point-student.html";
    }
    @GetMapping("/student/list")
    @ResponseBody
    public Map<String,Object> point_student_list(Integer page, Integer limit){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Point> content = pointService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",pointService.findAllCountOfStudent());
        return map;
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
        List<Point> content = pointService.findAll(pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        map.put("data",content);
        map.put("size",pointService.findAllCountOfStudent());
        return map;
    }
}
