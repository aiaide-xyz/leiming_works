package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;


public interface UserRepository extends CrudRepository<User,Long> {
    @Query(value = "select * from user where username = ?1",nativeQuery = true)
    User findByUsername(String username);
}
