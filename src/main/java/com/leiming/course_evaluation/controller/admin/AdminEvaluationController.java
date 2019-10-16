package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.Department;
import com.leiming.course_evaluation.dto.EvaluationRecording;
import com.leiming.course_evaluation.dto.Teacher;
import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.service.BatchService;
import com.leiming.course_evaluation.service.EvaluationRecordingService;
import com.leiming.course_evaluation.service.TeacherService;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.criteria.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 雷鸣
 * @ClassName Evaluation
 * LovelyLM
 * @Date 2019/9/30 9:43
 */
@Controller
@RequestMapping("/admin/evaluation")
public class AdminEvaluationController {
    @Autowired
    private TeachingManagementService teachingManagementService;
    @Autowired
    private EvaluationRecordingService evaluationRecordingService;
    @Autowired
    private BatchService batchService;
    @Autowired
    private TeacherService teacherService;

    @GetMapping("")
    public ModelAndView evaluation(Model model) {
        model.addAttribute("type", "student");

        return new ModelAndView("admin/evaluation-list.html");
    }

    @GetMapping("/department")
    public ModelAndView departmentEvaluation(Model model) {
        model.addAttribute("type", "department");
        return new ModelAndView("admin/evaluation-teacher-list.html");
    }

    @GetMapping("/list")
    @ResponseBody
    public Map<String, Object> evaluationList(String type) {
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "");
        map.put("count", 1000);
        if (type.equals("student")) {
            List<TeachingManagement> data = teachingManagementService.findAllList();
            for (TeachingManagement t : data) {
                String status = batchService.findByBatchName(t.getBatch()).getStatus();
                if (status.equals("正在评教")) {
                    t.setStatus2(status);
                } else if (status.equals("未开启")) {
                    t.setStatus2(status);
                } else if (status.equals("评教结束")) {
                    t.setStatus2(status);
                    List<EvaluationRecording> evaluationRecordings = evaluationRecordingService.findByClassAndCourse(t.getCgClass(), t.getCourse());
                    System.out.println(evaluationRecordings);
                    if (evaluationRecordings.size() ==0) {
                        t.setFinalScore("已重置");
                    }else {
                        float score = 0;
                        float numb = 0;
                        for (EvaluationRecording e : evaluationRecordings) {
                            numb++;
                            score += Float.parseFloat(e.getScore());
                        }
                        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                        String p = decimalFormat.format(score / numb);//format 返回的是字符串
                        if (p.length() == 3) {
                            p = "0" + p;
                        }
                        t.setFinalScore(p);
                    }
                }
                map.put("data", data);
            }
        } else if (type.equals("department")) {
            List<Teacher> teacherList = teacherService.findAllList();
            for (Teacher t : teacherList) {
                t.setStatus(t.getDepartment().getStatus());
                t.setDptName(t.getDepartment().getDptName());
                if (t.getDepartment().getStatus().equals("评教结束")) {
                    List<EvaluationRecording> evaluationRecordingList = evaluationRecordingService.findByTeacherNumber(t.getTeacherNumber());
                    if (evaluationRecordingList.size() == 0){
                        t.setFinalScore("已重置");
                    }else {
                        float score = 0;
                        float numb = 0;
                        for (EvaluationRecording e : evaluationRecordingList) {
                            numb++;
                            score += Float.parseFloat(e.getScore());
                        }
                        DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                        String p = decimalFormat.format(score / numb);//format 返回的是字符串
                        if (p.length() == 3) {
                            p = "0" + p;
                        }
                        t.setFinalScore(p);
                    }
                }
                map.put("data", teacherList);
            }
        }
        return map;
    }

    @GetMapping("/delete")
    @ResponseBody
    public String delete(Long id) {
        TeachingManagement teachingManagement = teachingManagementService.findById(id);
        List<EvaluationRecording> byClassAndCourse = evaluationRecordingService.findByClassAndCourse(teachingManagement.getCgClass(), teachingManagement.getCourse());
        if (byClassAndCourse != null && byClassAndCourse.size() != 0) {
            for (EvaluationRecording evaluationRecording : byClassAndCourse) {
                System.out.println(evaluationRecording.getId());
               evaluationRecordingService.deleteEvaluationRecording(evaluationRecording.getId());
            }
            return "ok";
        }

        return "no";
    }




    @GetMapping("/remove")
    @ResponseBody
    public String remove(Long id) {
        System.out.println(id);
        Teacher teacher = teacherService.findById(id);
        try {
            int i = evaluationRecordingService.deleteEvaluationRecordingByTeacherId(Long.valueOf(teacher.getTeacherNumber()));
            if (i == 0){
                return "not";
            }else {
                return "ok";
            }
            }
        catch (Exception e){
            return "no";
        }
    }

    //搜索教学方面
    @GetMapping("/LikeEvaluation")
    @ResponseBody
    public Map<String,Object> evaluation(Integer page, Integer limit, TeachingManagement teachingManagement){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<TeachingManagement> data = teachingManagementService.findAll(new Specification<TeachingManagement>(){
            @Override
            public Predicate toPredicate(Root<TeachingManagement> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据dptName 模糊查询Department
                if (StringUtils.isNotBlank(teachingManagement.getTeacher())) {
                    list.add(cb.like(root.get("teacher").as(String.class), "%" + teachingManagement.getTeacher() + "%"));
                }
                if (StringUtils.isNotBlank(teachingManagement.getCgClass())) {
                    list.add(cb.like(root.get("cgClass").as(String.class), "%" + teachingManagement.getCgClass() + "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        //List<TeachingManagement> data = teachingManagementService.findAllList();
        for (TeachingManagement t : data) {
            String status = batchService.findByBatchName(t.getBatch()).getStatus();
            if (status.equals("正在评教")) {
                t.setStatus2(status);
            } else if (status.equals("未开启")) {
                t.setStatus2(status);
            } else if (status.equals("评教结束")) {
                t.setStatus2(status);
                List<EvaluationRecording> evaluationRecordings = evaluationRecordingService.findByClassAndCourse(t.getCgClass(), t.getCourse());
                System.out.println(evaluationRecordings);
                if (evaluationRecordings.size() ==0) {
                    t.setFinalScore("已重置");
                }else {
                    float score = 0;
                    float numb = 0;
                    for (EvaluationRecording e : evaluationRecordings) {
                        numb++;
                        score += Float.parseFloat(e.getScore());
                    }
                    DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                    String p = decimalFormat.format(score / numb);//format 返回的是字符串
                    if (p.length() == 3) {
                        p = "0" + p;
                    }
                    t.setFinalScore(p);
                }
            }
            map.put("data", data);
        }
        return map;
    }


    //搜索系部方面
    @GetMapping("/LikeEvaluationTeacher")
    @ResponseBody
    public Map<String,Object> evaluationTeacher(Integer page, Integer limit, Teacher teacher){
        page--;
        Pageable pageable = PageRequest.of(page,limit);
        List<Teacher> teacherList = teacherService.findAll(new Specification<Teacher>(){
            @Override
            public Predicate toPredicate(Root<Teacher> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder cb) {
                List<Predicate> list = new ArrayList<>();
                //根据username 模糊查询Evaluation
                if (StringUtils.isNotBlank(teacher.getTeacherNumber())) {
                    list.add(cb.like(root.get("teacherNumber").as(String.class), "%" + teacher.getTeacherNumber() + "%"));
                }
                //根据dptName 查询Evaluation
                if (StringUtils.isNotBlank(teacher.getDptName())) {
                    Join<Department, Teacher> join = root.join("department", JoinType.LEFT);
                    list.add(cb.like(join.get("dptName"), "%" +teacher.getDptName()+ "%"));
                }
                Predicate[] pre = new Predicate[list.size()];
                criteriaQuery.where(list.toArray(pre));
                return cb.and(list.toArray(pre));
            }
        },pageable).getContent();
        Map<String,Object> map = new HashMap<>();
        for (Teacher t : teacherList) {
            t.setStatus(t.getDepartment().getStatus());
            t.setDptName(t.getDepartment().getDptName());
            if (t.getDepartment().getStatus().equals("评教结束")) {
                List<EvaluationRecording> evaluationRecordingList = evaluationRecordingService.findByTeacherNumber(t.getTeacherNumber());
                if (evaluationRecordingList.size() == 0){
                    t.setFinalScore("已重置");
                }else {
                    float score = 0;
                    float numb = 0;
                    for (EvaluationRecording e : evaluationRecordingList) {
                        numb++;
                        score += Float.parseFloat(e.getScore());
                    }
                    DecimalFormat decimalFormat = new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                    String p = decimalFormat.format(score / numb);//format 返回的是字符串
                    if (p.length() == 3) {
                        p = "0" + p;
                    }
                    t.setFinalScore(p);
                }
            }
            map.put("data", teacherList);
        }
        return map;
    }

}
