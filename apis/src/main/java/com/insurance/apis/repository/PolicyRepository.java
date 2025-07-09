package com.insurance.apis.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import com.insurance.apis.model.Policy;


@Repository
public class PolicyRepository {

    private static final String KEY = "POLICIES";

    private final RedisTemplate<String, Policy> redisTemplate;
    private final HashOperations<String, String, Policy> hashOperations;

    public PolicyRepository(RedisTemplate<String, Policy> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.hashOperations = redisTemplate.opsForHash();
    }

    public void save(Policy policy){
        hashOperations.put(KEY, policy.getId(), policy);
    }

    public Policy findById(String id){
        return hashOperations.get(KEY, id);
    }

    public List<Policy> findAll() {
        return new ArrayList<>(hashOperations.entries(KEY).values());
    }

    public void deleteById(String id) {
        hashOperations.delete(KEY, id);
    }

    public boolean existsById(String id) {
        return hashOperations.hasKey(KEY, id);
    }

    public List<Policy> findByInsuranceId(String insuranceId) {
        return hashOperations.entries(KEY).values()
        .stream()
        .filter(p -> insuranceId.equals(p.getInsuranceId()))
        .toList();
    }

}
