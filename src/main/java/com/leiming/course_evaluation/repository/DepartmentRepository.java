package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Department;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface DepartmentRepository extends JpaRepository<Department,Long> {
    @Query(value = "select * from department",nativeQuery = true)
    Page<Department> findAll(Pageable pageable);
    @Query(value = "select count(*) from department",nativeQuery = true)
    int findAllCount();
}
