package com.leiming.course_evaluation.controller.admin;

import com.leiming.course_evaluation.dto.Batch;
import com.leiming.course_evaluation.dto.EvaluationRecording;
import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.service.BatchService;
import com.leiming.course_evaluation.service.EvaluationRecordingService;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
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
    @GetMapping("")
    public ModelAndView evaluation(){
        return new ModelAndView("admin/evaluation-list.html");
    }
    @GetMapping("/list")
    @ResponseBody
    public Map<String,Object> evaluationList(){
        List<TeachingManagement> teachingManagementList = teachingManagementService.findAllList();
        for (TeachingManagement t:teachingManagementList) {
            String status = batchService.findByBatchName(t.getBatch()).getStatus();

            if (status.equals("正在评教")){
                t.setStatus2(status);
            }else if (status.equals("未开启")){
                t.setStatus2(status);
            }else if (status.equals("评教结束")){
                t.setStatus2(status);
                List<EvaluationRecording> evaluationRecordings = evaluationRecordingService.findByClassAndCourse(t.getCgClass(),t.getCourse());
                float score = 0;
                float numb = 0;
                if (evaluationRecordings!=null){
                    for (EvaluationRecording e:evaluationRecordings) {
                        numb++;
                        score += Float.parseFloat(e.getScore());
                    }
                    DecimalFormat decimalFormat=new DecimalFormat(".00");//构造方法的字符格式这里如果小数不足2位,会以0补足.
                    String p=decimalFormat.format(score/numb);//format 返回的是字符串
                    if (p.length()==3){
                        p="0"+p;
                    }
                    t.setFinalScore(p);
                }
            }
        }
        Map<String,Object> map = new HashMap<>();
        map.put("data",teachingManagementList);
        map.put("code",0);
        map.put("msg","");
        map.put("count",1000);
        return map;
    }


}
