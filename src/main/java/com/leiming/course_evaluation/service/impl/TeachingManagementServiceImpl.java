package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.TeachingManagement;
import com.leiming.course_evaluation.repository.TeachingManagementRepository;
import com.leiming.course_evaluation.service.TeachingManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TeachingManagementServiceImpl implements TeachingManagementService {
    @Autowired
    private TeachingManagementRepository teachingManagementRepository;
    @Override
    public Page<TeachingManagement> findAll(Pageable pageable) {
        return teachingManagementRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return teachingManagementRepository.findAllCount();
    }
}
