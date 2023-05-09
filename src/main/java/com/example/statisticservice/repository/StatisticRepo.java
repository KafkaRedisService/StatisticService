package com.example.statisticservice.repository;

import com.example.statisticservice.entity.Statistic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface StatisticRepo extends JpaRepository<Statistic, Integer> {
    @Query(value = "SELECT * FROM statistic ORDER BY id DESC LIMIT 0, 1", nativeQuery = true)
    Optional<Statistic> findByStatisticFinal();
}
