package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Admin;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<Admin,Long> {
    @Query(value = "select * from user where username = ?1",nativeQuery = true)
    Admin findByUsername(String username);
}
