package com.example.statisticservice.service;

import com.example.statisticservice.RedisExample;
import com.example.statisticservice.entity.Statistic;
import com.example.statisticservice.repository.StatisticRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StatisticService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private StatisticRepo statisticRepo;

    private RedisExample redisExample;

    @Autowired
    public StatisticService(StatisticRepo statisticRepo, RedisExample redisExample) {
        this.statisticRepo = statisticRepo;
        this.redisExample = redisExample;
    }


    @KafkaListener(id = "statisticGroup", topics = "statistic")
    public void listen(Statistic statistic) {
        logger.info("Received: "+ statistic.getMessage());
        statisticRepo.save(statistic);

        Optional<Statistic> statistic1 = statisticRepo.findByStatisticFinal();

        if(statistic1.isPresent()){
            redisExample.setKeyValueRedis(statistic1.get());
        }

    }
}
