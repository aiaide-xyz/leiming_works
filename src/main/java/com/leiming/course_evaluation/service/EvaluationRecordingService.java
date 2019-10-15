package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.EvaluationRecording;
import org.springframework.data.jpa.repository.JpaRepository;

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

    List<EvaluationRecording> findAll();

    List<EvaluationRecording> findByClassAndCourse(String cgClass, String course);
    EvaluationRecording findOneByNumber(String userNumber, String teacherNumber);


    List<EvaluationRecording> findByTeacherNumber(String teacherNumber);

    void deleteEvaluationRecording(Long id);

    void deleteEvaluationRecordingById(Long id);

    int deleteEvaluationRecordingByTeacherId(Long teacherNumber);
}
