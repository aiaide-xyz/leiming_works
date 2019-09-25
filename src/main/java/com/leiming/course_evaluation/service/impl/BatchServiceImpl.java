package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Batch;
import com.leiming.course_evaluation.repository.BatchRepository;
import com.leiming.course_evaluation.service.BatchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class BatchServiceImpl implements BatchService {
    @Autowired
    private BatchRepository batchRepository;

    @Override
    public Page<Batch> findAll(Pageable pageable) {
        return batchRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return batchRepository.findAllCount();
    }

    @Override
    public Batch findById(Long id) {
        return batchRepository.findById(id).get();
    }

    @Override
    public void saveOne(Batch batchNew) {
        batchRepository.save(batchNew);
    }

    @Override
    public List<Batch> findAllList() {
        return batchRepository.findAll();
    }
}
