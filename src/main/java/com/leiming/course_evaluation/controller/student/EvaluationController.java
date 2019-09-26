package com.leiming.course_evaluation.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("student/evaluation")
public class EvaluationController {
    @GetMapping("")
    public String toEvaluation(){
        return "student/evaluation-list.html";
    }
}
