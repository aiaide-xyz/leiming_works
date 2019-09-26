package com.leiming.course_evaluation.controller.student;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.service.PointService;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("student/evaluation")
public class EvaluationController {
    @Autowired
    private PointService pointService;
    @Autowired
    private TeachingManagementService teachingManagementService;
    @GetMapping("")
    public String toEvaluation(){
        return "student/evaluation-list.html";
    }
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> evaluationList(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("user");
        List<TeachingManagement> list = teachingManagementService.finAllByClass(student.getCgClass().getClassName());
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        map.put("count",1000);
        return map;
    }
    @RequestMapping("/do")
    public ModelAndView test(Model model,Long id){
        System.out.println(id);
        List<Point> pointList = pointService.findAllByType("student");
        System.out.println();
        TeachingManagement teachingManagement = teachingManagementService.findById(id);
        model.addAttribute("pointList",pointList);
        model.addAttribute("teachingManagement",teachingManagement);
        return new ModelAndView("student/evaluation.html","model",model);
    }
}
