package com.example.statisticservice;

import com.example.statisticservice.entity.Statistic;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class RedisExample {

    private RedisTemplate template;;
    @Autowired
    public RedisExample(RedisTemplate template) {
        this.template = template;
    }

    public void setKeyValueRedis(Statistic statistic) {
        // Set giá trị của key "id" là "message"
        template.opsForValue().set(statistic.getId().toString(),statistic.getMessage());

        // In ra màn hình Giá trị của key
        System.out.println("Gia tri key ANDONG: "+template.opsForValue().get(statistic.getId().toString()));
    }
}
