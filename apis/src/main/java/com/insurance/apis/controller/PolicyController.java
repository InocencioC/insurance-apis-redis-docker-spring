package com.insurance.apis.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.insurance.apis.model.Policy;
import com.insurance.apis.service.PolicyService;

@RestController
@RequestMapping("/policy")
public class PolicyController {

    private final PolicyService policyService;

    public PolicyController(PolicyService policyService) {
        this.policyService = policyService;
    }

    @PostMapping
    public ResponseEntity<Policy> createPolicy(@RequestBody Policy policy) {
        Policy savedPolicy = policyService.create(policy);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedPolicy);
    }

    @GetMapping
    public List<Policy> getAllPolicies() {
        return policyService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Policy> getPolicyById(@PathVariable String id) {
        Optional<Policy> policy = policyService.findById(id);
        // Usa o Optional para retornar 200 OK ou 404 Not Found
        return policy.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Policy> updatePolicy(@PathVariable String id, @RequestBody Policy updatedPolicy) {
        Optional<Policy> result = policyService.update(id, updatedPolicy);
        // Usa o Optional para retornar 200 OK ou 404 Not Found
        return result.map(ResponseEntity::ok)
                     .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePolicy(@PathVariable String id) {
        boolean deleted = policyService.delete(id);
        // Retorna 204 No Content se deletado, ou 404 Not Found se não encontrado
        return deleted ? ResponseEntity.noContent().build() : ResponseEntity.notFound().build();
    }

    // Opcional: Adicionar um endpoint para verificar existência
    @GetMapping("/exists/{id}")
    public ResponseEntity<Boolean> existsPolicy(@PathVariable String id) {
        boolean exists = policyService.existsById(id);
        return ResponseEntity.ok(exists);
    }
}