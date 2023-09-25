package com.example.testtask.repository;

import com.example.testtask.entity.CharInterval;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CharIntervalRepository extends IntervalRepository<Character, CharInterval> {
    @Override
    @Query(value = "SELECT id, start_i, end_i FROM #{#entityName} e ORDER BY ( ascii(e.end_i) - ascii(e.start_i)) ASC limit 1 ", nativeQuery = true)
    Optional<CharInterval> findMinInterval();
}
