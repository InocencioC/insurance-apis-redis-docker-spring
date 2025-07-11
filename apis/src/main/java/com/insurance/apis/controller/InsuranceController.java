package com.insurance.apis.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.insurance.apis.dto.InsuranceRequestDTO;
import com.insurance.apis.model.Insurance;
import com.insurance.apis.service.InsuranceService;
@RestController
@RequestMapping("/insurance")
public class InsuranceController {

    private final InsuranceService insuranceService;

    public InsuranceController(InsuranceService insuranceService){
        this.insuranceService = insuranceService;
    }

    @PostMapping
    public ResponseEntity<Insurance> create(@RequestBody InsuranceRequestDTO insuranceRequestDTO ){
        Insurance createdInsurance = insuranceService.create(insuranceRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdInsurance);
    }

    @GetMapping
    public List<Insurance> list(){
        return insuranceService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Insurance> search(@PathVariable UUID id){
        Insurance insurance = insuranceService.getById(id);
        return insurance != null ? ResponseEntity.ok(insurance) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id){
        insuranceService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
