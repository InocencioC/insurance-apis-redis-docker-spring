package com.insurance.apis.dto;

import java.sql.Date;
import com.insurance.apis.model.InsuranceType;

public record InsuranceRequestDTO(String clientName, InsuranceType insuranceType, Date startDate, Date endDate) {
}