package com.leiming.course_evaluation.service.impl;

import com.leiming.course_evaluation.dto.Admin;
import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import com.leiming.course_evaluation.repository.LoginAdminRepository;
import com.leiming.course_evaluation.service.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginServiceImpl implements LoginService {

    @Autowired
    private LoginAdminRepository loginAdminRepository;


    @Override
    public Admin AdminLogin(String username) {
        return loginAdminRepository.adminLogin(username);
    }

    @Override
    public Student studentLogin(String stuNumber) {
        return loginAdminRepository.studentLogin(stuNumber);
    }

    @Override
    public Teacher teacherLogin(String teacherNumber) {
        return loginAdminRepository.teacherLogin(teacherNumber);
    }
}
