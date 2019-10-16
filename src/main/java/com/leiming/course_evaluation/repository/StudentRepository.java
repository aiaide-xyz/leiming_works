package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Admin;
import com.leiming.course_evaluation.dto.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface StudentRepository extends JpaRepository<Student,Long> {
    @Query(value = "select * from student",nativeQuery = true)
    Page<Student> findAll(Pageable pageable);
    @Query(value = "select count(*) from student",nativeQuery = true)
    int findAllCount();

    @Modifying
    @Transactional
    @Query(value = "delete from student where id=?1",nativeQuery = true)
    int deleteStudent(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from Student where id in (?1)",nativeQuery = true)
    int deleteAllStudent(List<Long> ids);

    @Query(value = "from Student where stuNumber = ?1")
    Student findOneByNumber(String stuNumber);
    Page<Student> findAll(Specification<Student> spc, Pageable pageable);

}
