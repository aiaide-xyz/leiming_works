package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Student;
import com.leiming.course_evaluation.dto.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface TeacherRepository extends JpaRepository<Teacher,Long> {
    @Query(value = "select * from teacher",nativeQuery = true)
    Page<Teacher> findAll(Pageable pageable);
    @Query(value = "select count(*) from teacher",nativeQuery = true)
    int findAllCount();
    @Modifying
    @Transactional
    @Query(value = "delete from teacher where id=?1",nativeQuery = true)
    int deleteTeacher(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from teacher  where id in (?1)",nativeQuery = true)
    int deleteAllTeacher(List<Long> ids);
    @Query(value = "from Teacher where teacherNumber = ?1")
    Teacher findOneByNumber(String teacherNumber);

    Page<Teacher> findAll(Specification<Teacher> teacherSpecification, Pageable pageable);
}
