package com.insurance.apis.model;

import java.sql.Date;
import java.util.UUID;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Insurance {
    @Id
    private UUID id;
    private String clientName;
    private InsuranceType insuranceType;
    private Date startDate;
    private Date endDate;

    public void generateId() {
        this.id = UUID.randomUUID();
    }
}
