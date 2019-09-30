package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.EvaluationRecording;
import com.leiming.course_evaluation.repository.EvaluationRecordingRepository;
import com.leiming.course_evaluation.service.EvaluationRecordingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 雷鸣
 * @ClassName EvaluationRecordingServiceImpl
 * LovelyLM
 * @Date 2019/9/29 10:40
 */
@Service
public class EvaluationRecordingServiceImpl implements EvaluationRecordingService {
    @Autowired
    private EvaluationRecordingRepository evaluationRecordingRepository;
    @Override
    public List<EvaluationRecording> findByNumber(String stuNumber) {
        return evaluationRecordingRepository.findByNumber(stuNumber);
    }

    @Override
    public void save(EvaluationRecording evaluationRecording) {
        evaluationRecordingRepository.save(evaluationRecording);
    }
}
