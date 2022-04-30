package com.finance.loans.domain.model;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Payment {
    private int installmentNumber;
    private Double value;
    private Double valueAfterPayment;
    private LocalDate paymentDate;
}
