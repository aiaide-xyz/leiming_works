package com.leiming.course_evaluation.controller.teacher;

import com.leiming.course_evaluation.dto.*;
import com.leiming.course_evaluation.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("teacher/evaluation")
public class TeacherEvaluationController {
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private PointService pointService;
    @Autowired
    private EvaluationRecordingService evaluationRecordingService;

    /***
     * 获取学生评教列表的页面
     * @param request
     * @return
     */
    @GetMapping("")
    public ModelAndView toEvaluation(HttpServletRequest request, Model model){
        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        Department department = departmentService.findByID(teacher.getDepartment().getId());
        if (department.getStatus().equals("未开启")){
            model.addAttribute("msg","未开启");
        }else if (department.getStatus().equals("评教结束")){
            model.addAttribute("msg","评教结束");
        }
        return new ModelAndView("teacher/evaluation-list.html","model",model);
    }
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> evaluationList(HttpServletRequest request){

        Teacher teacher = (Teacher) request.getSession().getAttribute("user");
        List<Teacher> allList = teacherService.findAllList();

        List<Teacher> list = new ArrayList<>();
        for (Teacher teacher1 : allList) {
            teacher1.setDptName(teacher1.getDepartment().getDptName());
            if (teacher1.getDepartment().getDptName().equals(teacher.getDepartment().getDptName())){
                list.add(teacher1);
            }
            if (teacher1.getTeacherNumber().equals(teacher.getTeacherNumber())){
                list.remove(teacher1);
            }
        }

        if (evaluationRecordingService.findByNumber(teacher.getTeacherNumber()) != null){

            for (Teacher teacher1 : list) {
                if (evaluationRecordingService.findOneByNumber(teacher.getTeacherNumber(),teacher1.getTeacherNumber()) != null){
                        teacher1.setStatus("已评教");
                }

            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",list);
        map.put("code",0);
        map.put("msg","");
        map.put("count",1000);
        return map;
    }

    @RequestMapping("/do")
    public ModelAndView test(Model model, Long id){
        List<Point> pointList = pointService.findAllByType("department");
        Teacher teacher = teacherService.findById(id);
        teacher.setDptName(teacher.getDepartment().getDptName());
        model.addAttribute("teacher",teacher);
        model.addAttribute("pointList",pointList);
        return new ModelAndView("teacher/evaluation.html","model",model);
    }


}
