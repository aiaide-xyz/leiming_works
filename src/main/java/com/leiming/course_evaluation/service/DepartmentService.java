package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Department;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {
    Page<Department> findAll(Pageable pageable);
    int findAllCount();
}
