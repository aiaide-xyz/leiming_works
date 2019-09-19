package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Batch;
import com.leiming.course_evaluation.dto.CgClass;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface BatchService {
    Page<Batch> findAll(Pageable pageable);
    int findAllCount();

    Batch findById(Long id);

    void saveOne(Batch batchNew);
}
