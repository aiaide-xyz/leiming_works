package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.dto.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface PointRepository extends JpaRepository<Point,Long> {
    @Query(value = "select * from point",nativeQuery = true)
    Page<Point> findAll(Pageable pageable);
    @Query(value = "select count(*) from point where type = 'student'",nativeQuery = true)
    int findAllCountOfStudent();
    @Query(value = "select * from point where type ='student'",nativeQuery = true)
    Page<Point> findAllByStudent(Pageable pageable);
    @Query(value = "select * from point where type ='department'",nativeQuery = true)
    Page<Point> findAllByDepartment(Pageable pageable);
    @Query(value = "select * from point where type =?1",nativeQuery = true)
    List<Point> finAllByType(String type);
    @Modifying
    @Transactional
    @Query(value = "delete from point where id=?1",nativeQuery = true)
    int deletePoint(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from point  where id in (?1)",nativeQuery = true)
    int deleteAllPoint(List<Long> ids);
}
