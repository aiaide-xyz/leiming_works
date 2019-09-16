package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Admin;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface LoginAdminRepository extends JpaRepository<Admin,Long> {

    /**
     * 查询管理员
     * @param username
     * @return
     */
    @Query(value="from Admin where username=?1")
    Admin adminLogin(String username);

    /**
     * 查询学生
     * @param stuNum
     * @return
     */
    @Query(value="from Student where stuNumber=?1")
    Student studentLogin(String stuNum);

    /**
     * 查询教师
     * @param teaNum
     * @return
     */
    @Query(value = "from Teacher where teacherNumber=?1")
    Teacher teacherLogin(String teaNum);
}
