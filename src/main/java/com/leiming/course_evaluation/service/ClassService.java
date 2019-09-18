package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.CgClass;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ClassService {
    Page<CgClass> findAll(Pageable pageable);
    int findAllCount();

    List<CgClass> findAllList();
    CgClass findById(Long id);
}
