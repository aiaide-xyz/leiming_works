package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.TeachingManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface TeachingManagementRepository extends JpaRepository<TeachingManagement,Long> {
    @Query(value = "select * from teacher_management",nativeQuery = true)
    Page<TeachingManagement> findAll(Pageable pageable);
    @Query(value = "select count(*) from teacher_management",nativeQuery = true)
    int findAllCount();
}
