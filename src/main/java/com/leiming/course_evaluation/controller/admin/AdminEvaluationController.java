package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.EvaluationRecording;
import com.leiming.course_evaluation.dto.Teacher;
import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.service.BatchService;
import com.leiming.course_evaluation.service.EvaluationRecordingService;
import com.leiming.course_evaluation.service.TeacherService;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.text.DecimalFormat;
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

    @GetMapping("/deletes")
    @ResponseBody
    public String deletes(String[] id) {
        System.out.println(id);
        return "n";
    }


    @GetMapping("/remove")
    @ResponseBody
    public String remove(Long id) {
        Teacher teacher = teacherService.findById(id);
        try {
            int i = evaluationRecordingService.deleteEvaluationRecordingByTeacherId(teacher.getTeacherNumber());
            if (i == 1){
                return "ok";
            }
            else {
                return "not";
            }


        }
        catch (Exception e){
            return "no";
        }


    }


}
