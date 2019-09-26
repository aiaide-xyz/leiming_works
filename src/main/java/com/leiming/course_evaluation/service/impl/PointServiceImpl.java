package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Point;
import com.leiming.course_evaluation.repository.PointRepository;
import com.leiming.course_evaluation.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private PointRepository pointRepository;
    @Override
    public Page<Point> findAll(Pageable pageable) {
        return pointRepository.findAll(pageable);
    }

    @Override
    public Page<Point> findAllByDepartment(Pageable pageable) {
        return pointRepository.findAllByDepartment(pageable);
    }

    @Override
    public Page<Point> findAllByStudent(Pageable pageable) {
        return pointRepository.findAllByStudent(pageable);
    }

    @Override
    public int findAllCountOfStudent() {
        return pointRepository.findAllCountOfStudent();
    }

    @Override
    public List<Point> findAllByType(String type) {
        return pointRepository.finAllByType(type);
    }


    @Override
    public Point finById(Long aLong) {
        return pointRepository.findById(aLong).get();
    }

    @Override
    public void Save(Point point) {
        pointRepository.save(point);
    }
}
