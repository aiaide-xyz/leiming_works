package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.repository.StudentRepository;
import com.leiming.course_evaluation.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;


@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Override
    public Page<Student> findAll(Pageable pageable) {
        return studentRepository.findAll(pageable);
    }

    @Override
    public int findAllCount() {
        return studentRepository.findAllCount();
    }

    @Override
    public Student findById(Long id) {
        return studentRepository.findById(id).get();
    }

    @Override
    public void saveOne(Student student) {
        studentRepository.save(student);
    }
}
