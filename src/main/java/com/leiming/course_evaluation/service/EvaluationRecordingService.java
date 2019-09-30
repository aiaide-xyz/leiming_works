package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.EvaluationRecording;

import java.util.List;

/**
 * @author 雷鸣
 * @ClassName EvaluationRecordingService
 * LovelyLM
 * @Date 2019/9/29 10:39
 */
public interface EvaluationRecordingService {
    List<EvaluationRecording> findByNumber(String stuNumber);

    void save(EvaluationRecording evaluationRecording);
}
