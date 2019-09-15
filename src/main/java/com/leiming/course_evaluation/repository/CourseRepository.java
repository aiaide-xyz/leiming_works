package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.CgClass;
import com.leiming.course_evaluation.dto.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CourseRepository extends JpaRepository<Course,Long> {
    @Query(value = "select * from course",nativeQuery = true)
    Page<Course> findAll(Pageable pageable);
    @Query(value = "select count(*) from course",nativeQuery = true)
    int findAllCount();
}
