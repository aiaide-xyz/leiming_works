package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.EvaluationRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface EvaluationRecordingRepository extends JpaRepository<EvaluationRecording,Long> {
    @Query(value = "select * from evaluation_recording where user_number=?1",nativeQuery = true)
    List<EvaluationRecording> findByNumber(String stuNumber);
    @Query(value = "select * from evaluation_recording WHERE class_name=?1 AND course=?2 and user_type='student'",nativeQuery = true)
    List<EvaluationRecording> findByClassAndCourse(String cgClass, String course);
    @Query(value = "select * from evaluation_recording where user_number=?1 and teacher_number=?2 and user_type='department'",nativeQuery = true)
    EvaluationRecording findOneByNumber(String userNumber, String teacherNumber);
    @Query(value = "select * from evaluation_recording where user_number=?1 and user_type='department'",nativeQuery = true)
    List<EvaluationRecording> findByTeacherNumber(String teacherNumber);

    @Query(value = "delete from evaluation_recording where id = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    void deleteEvaluationRecording(Long id);

    @Query(value = "delete from evaluation_recording where teacher_number = ?1",nativeQuery = true)
    @Modifying
    @Transactional
    int deleteEvaluationRecordingByTeacherId(String id);


}
