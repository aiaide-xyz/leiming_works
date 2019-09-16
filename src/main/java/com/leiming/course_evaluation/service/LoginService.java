package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Admin;
import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;

public interface LoginService {
    /**
     * 管理员登陆
     *
     * @param username
     * @return
     */
    Admin AdminLogin(String username);

    /**
     * 根据学号查询学生对象
     * @param stuNumber
     * @return
     */
    Student studentLogin(String stuNumber);

    /**
     * 根据教师工号查询教师对象
     * @param teacherNumber
     * @return
     */
    Teacher teacherLogin(String teacherNumber);
}
