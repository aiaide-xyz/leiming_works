package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TeacherService {
    Page<Teacher> findAll(Pageable pageable);
    int findAllCount();

    Teacher findById(Long id);

    void saveOne(Teacher teacherNew);
    int deleteTeacher(int id);
    int deleteAllTeacher(List<Long> teaList);

    Teacher findOneByNumber(String teacherNumber);
}
