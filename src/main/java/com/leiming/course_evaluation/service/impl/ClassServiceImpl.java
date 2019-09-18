package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.CgClass;
import com.leiming.course_evaluation.dto.Department;
import com.leiming.course_evaluation.repository.ClassRepository;
import com.leiming.course_evaluation.repository.DepartmentRepository;
import com.leiming.course_evaluation.service.ClassService;
import com.leiming.course_evaluation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {

    @Autowired
    private ClassRepository classRepository;
    @Override
    public Page<CgClass> findAll(Pageable pageable) {
        return classRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return classRepository.findAllCount();
    }

    @Override
    public List<CgClass> findAllList() {
        return classRepository.findAll();
    }

    @Override
    public CgClass findById(Long id) {
        return classRepository.findById(id).get();
    }
}
