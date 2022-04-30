package com.finance.loans.domain.model;

import lombok.Data;
import java.time.LocalDateTime;
import java.util.List;

public @Data class Loan {

    private String id;
    private String bank;
    private double value;
    private LocalDateTime disbursementDate;
    private double installmentValue;
    private double currentValue;
    private float monthlyInterestRate;
    private float annualisedInterestRate;
    private int scheduledInstallments;
    private LocalDateTime lastUpdateDate;
    private List<Payment> payments;
}
