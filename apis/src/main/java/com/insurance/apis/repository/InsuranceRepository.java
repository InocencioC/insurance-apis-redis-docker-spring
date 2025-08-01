package com.insurance.apis.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.insurance.apis.model.Insurance;
@Repository
public class InsuranceRepository {
    
    private final RedisTemplate<String, Insurance> redisTemplate;
    private final HashOperations<String, UUID, Insurance> hashOps;

    public InsuranceRepository(RedisTemplate<String, Insurance> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOps = redisTemplate.opsForHash();
    }

    private final String KEY = "Insurance";

    public void save(Insurance insurance){
    if(insurance.getId() == null){
        insurance.setId(UUID.randomUUID());
      }
        hashOps.put(KEY, insurance.getId(), insurance); 
    }

    public Insurance findById(UUID id){
        return hashOps.get(KEY, id);
    }

    public List<Insurance> findAll() {
        return new ArrayList<>(hashOps.entries(KEY).values());
    }

    public void deleteById(UUID id) {
        hashOps.delete(KEY, id);
    }
    public boolean existsById(UUID id){
        return hashOps.hasKey(KEY, id);
    }
}
