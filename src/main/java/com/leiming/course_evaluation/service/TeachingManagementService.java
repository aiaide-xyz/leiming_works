package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.TeachingManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TeachingManagementService {
    Page<TeachingManagement> findAll(Pageable pageable);
    int findAllCount();
}
