package com.insurance.apis.service;

import org.springframework.stereotype.Service;

import java.util.*;

import com.insurance.apis.dto.InsuranceRequestDTO;
import com.insurance.apis.model.Insurance;
import com.insurance.apis.repository.InsuranceRepository;

@Service
public class InsuranceService {
    private final InsuranceRepository repository;

    public InsuranceService(InsuranceRepository repository) {
        this.repository = repository;
    }
    public Insurance create(InsuranceRequestDTO requestDTO) {
        
        Insurance insurance = Insurance.builder()
            .clientName(requestDTO.clientName())
            .insuranceType(requestDTO.insuranceType())
            .startDate(requestDTO.startDate())
            .endDate(requestDTO.endDate())
            .build();

            insurance.generateId();

        repository.save(insurance);
        return insurance;
    }
    
    public Insurance getById(UUID id) {
        return repository.findById(id);
    }
    public List<Insurance> getAll() {
        return repository.findAll();
    }
    public void delete(UUID id) {
        repository.deleteById(id);
    }
    public boolean exists(UUID id) {
        return repository.existsById(id);
    }
}
