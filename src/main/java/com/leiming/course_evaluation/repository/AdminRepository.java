package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface AdminRepository extends JpaRepository<Admin,Long> {

    /**
     * 通过原密码查找管理员
     * @param oldPassword
     * @return
     */
    @Query(value = "from Admin where password = ?1")
    Admin findOneByPassword(String oldPassword);
}
