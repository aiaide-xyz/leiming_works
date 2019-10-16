package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.TeachingManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.util.List;

@Component
public interface TeachingManagementRepository extends JpaRepository<TeachingManagement,Long> {
    @Query(value = "select * from teaching_management",nativeQuery = true)
    Page<TeachingManagement> findAll(Pageable pageable);
    @Query(value = "select count(*) from teaching_management",nativeQuery = true)
    int findAllCount();
    @Modifying
    @Transactional
    @Query(value = "delete from teaching_management where id=?1",nativeQuery = true)
    int deleteTeacherManagement(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from teaching_management  where id in (?1)",nativeQuery = true)
    int deleteAllTeacherManagement(List<Long> ids);
    @Query(value = "select * from teaching_management where cg_class=?1",nativeQuery = true)
    List<TeachingManagement> finAllByClass(String className);
    @Query(value = "select batch from teaching_management where cg_class=?1 LIMIT 0,1",nativeQuery = true)
    String finBatchByClass(String className);
    @Query(value = "from TeachingManagement where cgClass = ?1 and course = ?2")
    TeachingManagement findOneByClassAndCourse(String cgClass, String course);

    Page<TeachingManagement> findAll(Specification<TeachingManagement> teachingManagementSpecification, Pageable pageable);

}
