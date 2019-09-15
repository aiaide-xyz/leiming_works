package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

@Component
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query(value = "select * from teacher",nativeQuery = true)
    Page<Teacher> findAll(Pageable pageable);
    @Query(value = "select count(*) from teacher",nativeQuery = true)
    int findAllCount();
}
