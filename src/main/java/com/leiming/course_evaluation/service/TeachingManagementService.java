package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.TeachingManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;

public interface TeachingManagementService {
    Page<TeachingManagement> findAll(Pageable pageable);
    int findAllCount();
    List<TeachingManagement> findAllList();
    int deleteTeacherManagement(int id);
    int deleteAllTeacherManagement(List<Long> teaList);
    TeachingManagement findById(Long id);
    void saveOne(TeachingManagement teachingManagement);


    List<TeachingManagement> finAllByClass(String className);

    String findBatchByClass(String className);
    TeachingManagement findOneByClassAndCourse(String cgClass, String course);
    Page<TeachingManagement> findAll(Specification<TeachingManagement> teachingManagementSpecification, Pageable pageable);
}
