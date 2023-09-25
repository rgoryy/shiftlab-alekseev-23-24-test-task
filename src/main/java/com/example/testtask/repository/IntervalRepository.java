package com.example.testtask.repository;

import com.example.testtask.entity.AbstractIntervalEntity;
import jakarta.persistence.MappedSuperclass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.Optional;

@MappedSuperclass
@NoRepositoryBean
public interface IntervalRepository <T extends Comparable<T>, E extends AbstractIntervalEntity<T>> extends JpaRepository<E, Long> {
    @Query(value = "SELECT id, start_i, end_i FROM #{#entityName} e ORDER BY ( e.end_i - e.start_i) ASC limit 1 ", nativeQuery = true)
    Optional<E> findMinInterval();
}
