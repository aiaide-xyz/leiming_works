package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Department;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface DepartmentService {
    Page<Department> findAll(Pageable pageable);
    int findAllCount();
    Department updateById(Long id,String dptName,String mark);

    List<Department> findAllList();
    Department findByID(Long id);

    Department findById(Long id);

    void saveOne(Department departmentNew);
    int deleteDepartment(int id);
    int deleteAllDepartment(List<Long> depList);
    Department findOneByName(String dptName);
    Page<Department> findAll(Specification<Department> departmentSpecification, Pageable pageable);
}
