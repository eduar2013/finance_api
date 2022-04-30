package com.finance.loans.infrastructure.db.entities;

import com.finance.loans.domain.model.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@Document
public class PaymentEntity {

    public PaymentEntity(Payment payment){
        BeanUtils.copyProperties(payment,this);
    }

    @Field(name = "installment_number")
    private int installmentNumber;

    private Double value;

    @Field(name="value_after_payment")
    private Double valueAfterPayment;

    @Field(name="payment_date")
    private LocalDate paymentDate;
}
