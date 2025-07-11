package com.insurance.apis.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.insurance.apis.model.Policy;
import com.insurance.apis.repository.PolicyRepository;
@Service
public class PolicyService {
    private final PolicyRepository policyRepository;

    public PolicyService(PolicyRepository policyRepository) {
        this.policyRepository = policyRepository;
    }

    public Policy create(Policy policy) {
        // Exemplo: Gerar um ID se não tiver
        if (policy.getId() == null || policy.getId().isEmpty()) {
            policy.setId(UUID.randomUUID().toString());
        }
        policyRepository.save(policy);
        return policy;
    }

    public List<Policy> getAll() {
        return policyRepository.findAll();
    }

    // Retorna Optional<Policy> para indicar que pode não haver resultado
    public Optional<Policy> findById(String id) {
        return Optional.ofNullable(policyRepository.findById(id)); // Assumindo que findById do repo retorna Policy ou null
    }

    public Optional<Policy> update(String id, Policy updatedPolicy) {
        // Primeiro, verifique se a política existe
        return findById(id)
                .map(existingPolicy -> {
                    // Atualiza os campos necessários
                    existingPolicy.setNumber(updatedPolicy.getNumber());
                    policyRepository.save(existingPolicy); // Salva a política atualizada
                    return existingPolicy;
                });
    }

    public boolean delete(String id) {
        if (policyRepository.existsById(id)) { // Verifique se existe antes de tentar deletar
            policyRepository.deleteById(id);
            return true; // Deletado com sucesso
        }
        return false; // Não encontrado para deletar
    }

    public boolean existsById(String id) {
        return policyRepository.existsById(id);
    }
}
