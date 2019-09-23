package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.repository.PointRepository;
import com.leiming.course_evaluation.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointRepository pointRepository;
    @Override
    public Page<Point> findAll(Pageable pageable) {
        return pointRepository.findAll(pageable);
    }

    @Override
    public int findAllCountOfStudent() {
        return pointRepository.findAllCountOfStudent();
    }
}