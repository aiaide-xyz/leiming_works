package com.leiming.course_evaluation.service;

import com.leiming.course_evaluation.dto.Admin;
import org.springframework.stereotype.Component;


public interface AdminService   {
    Admin login();
     //通过密码查询管理员
    Admin findOneByPassword(String oldPassword);

    //保存管理员
    void save(Admin admin);

}
