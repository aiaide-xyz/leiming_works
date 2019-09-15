package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.CgClass;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface ClassRepository extends JpaRepository<CgClass,Long> {
    @Query(value = "select * from cg_class",nativeQuery = true)
    Page<CgClass> findAll(Pageable pageable);
    @Query(value = "select count(*) from cg_class",nativeQuery = true)
    int findAllCount();
}
