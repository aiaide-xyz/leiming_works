package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface BatchRepository extends JpaRepository<Batch,Long> {
    @Query(value = "select count(*) from batch",nativeQuery = true)
    int findAllCount();
}
