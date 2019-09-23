package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.dto.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PointRepository extends JpaRepository<Point,Long> {
    @Query(value = "select * from point",nativeQuery = true)
    Page<Point> findAll(Pageable pageable);
    @Query(value = "select count(*) from point where type = 'student'",nativeQuery = true)
    int findAllCountOfStudent();
}
