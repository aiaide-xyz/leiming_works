package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.EvaluationRecording;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface EvaluationRecordingRepository extends JpaRepository<EvaluationRecording,Long> {
    @Query(value = "select * from evaluation_recording where user_number=?1",nativeQuery = true)
    List<EvaluationRecording> findByNumber(String stuNumber);
}
