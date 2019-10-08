package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
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
    //添加与修改的方法
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
    //删除单个指标
    @PostMapping("/deletePoint")
    @ResponseBody
    public int deletePoint(int id){
        int i =pointService.deletePoint(id);
        System.out.println("======"+id);
        return i;
    }
    //删除多个指标
    @PostMapping("/deleteAllPoint")
    @ResponseBody
    public String deleteAllPoint(@RequestParam("id") String id){
        // 接收包含stuId的字符串，并将它分割成字符串数组
        String[] poiList = id.split(",");
        // 将字符串数组转为List<Intger> 类型
        List<Long> LString = new ArrayList<Long>();
        for(String str : poiList){
            LString.add(new Long(str));
        }
        System.out.println("====="+LString);
        // 调用service层的批量删除函数
        pointService.deleteAllPoint(LString);
        return "ok";
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
