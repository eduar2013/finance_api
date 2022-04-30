package com.finance.loans.infrastructure.rest.dto;

import com.finance.loans.domain.model.Payment;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class PaymentDto {

    private int installmentNumber;
    private Double value;
    private Double valueAfterPayment;
    private LocalDate paymentDate;

    public Payment paymentDtoToPayment(){
        Payment payment = new Payment();
        BeanUtils.copyProperties(this,payment);
        return  payment;
    }

    public PaymentDto(Payment p){
        BeanUtils.copyProperties(p,this);
    }
}
