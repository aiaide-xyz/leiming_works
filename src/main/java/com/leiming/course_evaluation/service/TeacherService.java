package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeacherService {
    Page<Teacher> findAll(Pageable pageable);
    int findAllCount();
}
