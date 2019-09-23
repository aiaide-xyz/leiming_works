package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Point;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface PointService {
    Page<Point> findAll(Pageable pageable);
    int findAllCountOfStudent();
}
