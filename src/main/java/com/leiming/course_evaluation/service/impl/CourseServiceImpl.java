package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Course;
import com.leiming.course_evaluation.repository.CourseRepository;
import com.leiming.course_evaluation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;
    @Override
    public Page<Course> findAll(Pageable pageable) {
        return courseRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return courseRepository.findAllCount();
    }

    @Override
    public Course findById(Long id) {
        return courseRepository.findById(id).get();
    }

    @Override
    public void saveOne(Course courseNew) {
        courseRepository.save(courseNew);
    }

    @Override
    public int deleteCourse(int id) {
        return courseRepository.deleteCourse(id);
    }

    @Override
    public int deleteAllCourse(List<Long> couList) {
        return courseRepository.deleteAllCourse(couList);
    }

    @Override
    public Course findOneByName(String courseName) {
        return courseRepository.findOneByName(courseName);
    }

    @Override
    public List<Course> findAllList() {
        return courseRepository.findAll();
    }
}
