package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Department;
import com.leiming.course_evaluation.repository.DepartmentRepository;
import com.leiming.course_evaluation.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;
    @Override
    public Page<Department> findAll(Pageable pageable) {
        return departmentRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return departmentRepository.findAllCount();
    }
    @Override
    public Department updateById(Long id,String dptName,String mark) {
        return departmentRepository.updateById(id,dptName,mark);
    }
}
