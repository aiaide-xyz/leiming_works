package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PointService {
    Page<Point> findAll(Pageable pageable);
    Page<Point> findAllByDepartment(Pageable pageable);
    Page<Point> findAllByStudent(Pageable pageable);
    int findAllCountOfStudent();
    List<Point> findAllByType(String type);
    Point finById(Long aLong);
    int deletePoint(int id);
    int deleteAllPoint(List<Long> poiList);
    void Save(Point point);
}
