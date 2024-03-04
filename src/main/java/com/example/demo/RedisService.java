package com.example.demo;
//RedisService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

//RedisService.java
@Service
public class RedisService {

 @Autowired
 private RedisTemplate<String, Object> redisTemplate;

 public Long incrementCounter(String key) {
     return redisTemplate.opsForValue().increment(key);
 }

 public Long getCounter(String key) {
     return (Long) redisTemplate.opsForValue().get(key);
 }

 public void resetCounter(String key) {
     redisTemplate.delete(key);
 }
}

