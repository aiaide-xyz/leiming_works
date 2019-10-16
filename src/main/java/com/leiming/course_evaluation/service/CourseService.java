package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface CourseService {
    Page<Course> findAll(Pageable pageable);
    int findAllCount();

    Course findById(Long id);

    void saveOne(Course courseNew);

    int deleteCourse(int id);
    int deleteAllCourse(List<Long> couList);
    Course findOneByName(String courseName);

    List<Course> findAllList();
    Page<Course> findAll(Specification<Course> courseSpecification, Pageable pageable);
}
