package com.insurance.apis.service;

import org.springframework.stereotype.Service;

import java.util.*;
import com.insurance.apis.model.Insurance;
import com.insurance.apis.repository.InsuranceRepository;

@Service
public class InsuranceService {
    private final InsuranceRepository repository;

    public InsuranceService(InsuranceRepository repository) {
        this.repository = repository;
    }
    public Insurance create(Insurance insurance) {
        repository.save(insurance);
        return insurance;
    }
    public Insurance getById(String id) {
        return repository.findById(id);
    }
    public List<Insurance> getAll() {
        return repository.findAll();
    }
    public void delete(String id) {
        repository.deleteById(id);
    }
    public boolean exists(String id) {
        return repository.existsById(id);
    }
}
