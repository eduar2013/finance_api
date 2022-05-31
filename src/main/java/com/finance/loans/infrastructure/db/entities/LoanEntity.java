package com.finance.loans.infrastructure.db.entities;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.model.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Document(collection = "loans")
@Data
@NoArgsConstructor
public class LoanEntity{

    @Id
    private String id;

    @Field(name = "initial_value")
    private double initialValue;

    @Field(name = "installment_value")
    private double installmentValue;

    private String bank;

   @Field(name = "disbursement_date")
   private LocalDateTime disbursementDate;

    private double value;

    @Field(name = "current_value")
    private double currentValue;

    @Field(name = "monthly_interest_rate")
    private float monthlyInterestRate;

    @Field(name = "annualised_interest_rate")
    private float annualisedInterestRate;

    @Field(name = "scheduled_installments")
    private int scheduledInstallments;

    @Field(name = "last_update_date")
    private LocalDateTime lastUpdateDate;

    private List<PaymentEntity> payments;


    public Loan toLoan() {
        Loan loan = new Loan();
        BeanUtils.copyProperties(this,loan);

        loan.setPayments(this.getPayments().stream().map(x-> {
            Payment payment = new Payment();
            BeanUtils.copyProperties(x,payment);
            return payment;
        }).collect(Collectors.toList()));


        return loan;
    }

    public LoanEntity(Loan loan){
        BeanUtils.copyProperties(loan,this);
        this.setPayments(loan.getPayments().stream().map(PaymentEntity::new).collect(Collectors.toList()));
    }
}
