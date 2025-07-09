package com.insurance.apis.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Policy implements Serializable {
    @Id
    private String id;
    private String insuranceId;
    private String number;
    private BigDecimal value;
    private Situation situation;

    public Policy(String insuranceId, String number, BigDecimal value, Situation situation){
        this.insuranceId = insuranceId;
        this.number = number;
        this.value = value;
        this.situation = situation;
    }

    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }
}
