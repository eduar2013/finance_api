package com.finance.loans.infrastructure.rest.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.finance.loans.domain.model.Loan;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


@Data
@NoArgsConstructor
public class LoanDto {

    private String id;
    private double value;
    private String bank;
    private LocalDateTime disbursementDate;
    private double installmentValue;

    @JsonFormat(shape = JsonFormat.Shape.NUMBER)
    private double currentValue;
    private float monthlyInterestRate;
    private float annualisedInterestRate;
    private int scheduledInstallments;
    private LocalDateTime lastUpdateDate;
    private List<PaymentDto> payments;

     public LoanDto(Loan loan) {
        BeanUtils.copyProperties(loan, this);

        this.setPayments(
                loan.getPayments().stream().map(PaymentDto::new).collect(Collectors.toList())
        );
    }


    public Loan loanDtoToLoan(){
        Loan loan = new Loan();
        BeanUtils.copyProperties(this, loan);

        loan.setPayments(payments.stream().map(PaymentDto::paymentDtoToPayment).collect(Collectors.toList()));
        return loan;
    }
}
