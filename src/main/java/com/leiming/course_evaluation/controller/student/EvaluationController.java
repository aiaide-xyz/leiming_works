package com.leiming.course_evaluation.controller.student;

import com.leiming.course_evaluation.dto.*;
import com.leiming.course_evaluation.service.BatchService;
import com.leiming.course_evaluation.service.EvaluationRecordingService;
import com.leiming.course_evaluation.service.PointService;
import com.leiming.course_evaluation.service.TeachingManagementService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.thymeleaf.util.StringUtils;

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
    @Autowired
    private BatchService batchService;
    @Autowired
    private EvaluationRecordingService evaluationRecordingService;

    /***
     * 获取学生评教列表的页面
     * @param request
     * @return
     */
    @GetMapping("")
    public String toEvaluation(HttpServletRequest request,Model model){
        //获取session中的student对象
        Student student = (Student) request.getSession().getAttribute("user");
        //获取学生的班级
        String className = student.getCgClass().getClassName();
        String batch = teachingManagementService.findBatchByClass(className);
        if (batchService.findByBatchName(batch).getStatus().equals("未开启")){
            model.addAttribute("msg","此班级所在批次未开启评教");
        }
        return "student/evaluation-list.html";
    }
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> evaluationList(HttpServletRequest request){
        Student student = (Student) request.getSession().getAttribute("user");
        List<TeachingManagement> list = teachingManagementService.finAllByClass(student.getCgClass().getClassName());
        List<EvaluationRecording> evaluationRecordings = evaluationRecordingService.findByNumber(student.getStuNumber());
        if (evaluationRecordings!=null){
            for (TeachingManagement t:list) {
                for (EvaluationRecording e:evaluationRecordings) {
                    if (t.getCourse().equals(e.getCourse())&&t.getTeacher().equals(e.getTeacher())){
                        t.setStatus("已评教");
                    }
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
    public ModelAndView test(Model model,Long id){
        System.out.println(id);
        List<Point> pointList = pointService.findAllByType("student");
        System.out.println();
        TeachingManagement teachingManagement = teachingManagementService.findById(id);
        model.addAttribute("pointList",pointList);
        model.addAttribute("teachingManagement",teachingManagement);
        return new ModelAndView("student/evaluation.html","model",model);
    }
    @RequestMapping("/save")
    @ResponseBody
    public String save(String score,String selectedContent,HttpServletRequest request,TeachingManagement teachingManagement){
        System.out.println(teachingManagement.getTeacher());
        EvaluationRecording evaluationRecording = new EvaluationRecording();
        if (request.getSession().getAttribute("user") instanceof Student){
            Student student = (Student)request.getSession().getAttribute("user");
            evaluationRecording.setBatch(teachingManagement.getBatch());
            evaluationRecording.setClassName(student.getCgClass().getClassName());
            evaluationRecording.setCourse(teachingManagement.getCourse());
            evaluationRecording.setUsername(student.getUsername());
            evaluationRecording.setUserNumber(student.getStuNumber());
            evaluationRecording.setScore(score);
            evaluationRecording.setTeacher(teachingManagement.getTeacher());
            evaluationRecording.setUserType("student");
            evaluationRecording.setDepartment(student.getDepartment().getDptName());
            evaluationRecording.setSelectedContent(selectedContent);
        }else if (request.getSession().getAttribute("user") instanceof Teacher){
            Teacher teacher = (Teacher)request.getSession().getAttribute("user");
        }
        try {
            evaluationRecordingService.save(evaluationRecording);
            return "ok";

        }
        catch (Exception e){
            return "no";
        }
    }





}
