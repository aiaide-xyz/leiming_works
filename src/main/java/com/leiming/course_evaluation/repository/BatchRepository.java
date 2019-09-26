package com.leiming.course_evaluation.repository;

import com.leiming.course_evaluation.dto.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
public interface BatchRepository extends JpaRepository<Batch,Long> {
    @Query(value = "select count(*) from batch",nativeQuery = true)
    int findAllCount();

    @Modifying
    @Transactional
    @Query(value = "delete from batch where id=?1",nativeQuery = true)
    int deleteBatch(int id);
    @Modifying
    @Transactional
    @Query(value = "delete from batch  where id in (?1)",nativeQuery = true)
    int deleteAllBatch(List<Long> ids);
}
