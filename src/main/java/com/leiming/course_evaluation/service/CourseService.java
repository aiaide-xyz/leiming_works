package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CourseService {
    Page<Course> findAll(Pageable pageable);
    int findAllCount();
}
