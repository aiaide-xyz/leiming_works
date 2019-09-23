package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Teacher;
import com.leiming.course_evaluation.repository.TeacherRepository;
import com.leiming.course_evaluation.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherRepository teacherRepository;
    @Override
    public Page<Teacher> findAll(Pageable pageable) {
        return teacherRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return teacherRepository.findAllCount();
    }

    @Override
    public Teacher findById(Long id) {
        return teacherRepository.findById(id).get();
    }

    @Override
    public void saveOne(Teacher teacherNew) {
        teacherRepository.save(teacherNew);
    }
    @Override
    public int deleteTeacher(int id) {
        return teacherRepository.deleteTeacher(id);
    }

    @Override
    public int deleteAllTeacher(List<Long> teaList) {
        return teacherRepository.deleteAllTeacher(teaList);
    }
    @Override
    public Teacher findOneByNumber(String teacherNumber) {
        return teacherRepository.findOneByNumber(teacherNumber);
    }
}
