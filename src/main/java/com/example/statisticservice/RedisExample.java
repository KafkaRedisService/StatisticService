package com.example.statisticservice;

import com.example.statisticservice.entity.Statistic;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public void listExample(List<Statistic> statisticsList) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = mapper.writeValueAsString(statisticsList);

//        List<String> list = new ArrayList<>();
//        for (Statistic stt: statisticsList) {
//            list.add(stt.getMessage());
//        }

        // set value key  statistic_list
        template.opsForList().rightPushAll("statistic_list", jsonInString);

        System.out.println("Size of key statistic_list: "+template.opsForList().size("statistic_list"));
        System.out.println("Data of key statistic_list: "+template.opsForList().range("statistic_list", 0,2));
    }
}
