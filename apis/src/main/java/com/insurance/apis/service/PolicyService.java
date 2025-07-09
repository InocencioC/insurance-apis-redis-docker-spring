package com.insurance.apis.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.insurance.apis.model.Policy;
import com.insurance.apis.repository.PolicyRepository;

@Service
public class PolicyService {
    private final PolicyRepository repository;

    public PolicyService (PolicyRepository repository) {
        this.repository = repository;
    }

    public Policy create(Policy policy) {
        policy.generateId();
        repository.save(policy);
        return policy;
    }

    public Policy getById(String id) {
        return repository.findById(id);
    }

    public List<Policy> getAll() {
        return repository.findAll();
    }
    public void delete(String id) {
        repository.deleteById(id);
    }

    public List<Policy> getByInsuranceId(String insuranceId) {
        return repository.findByInsuranceId(insuranceId);
    }
}
