package com.finance.loans.infrastructure.rest;

import com.finance.loans.domain.services.FindLoanService;
import com.finance.loans.domain.services.SaveLoanService;
import com.finance.loans.infrastructure.rest.dto.PaymentDto;
import com.finance.loans.infrastructure.rest.dto.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@RestController
@RequestMapping("/finance/api/v1/payment")
public class PaymentResource {

    @Autowired
    SaveLoanService saveLoanService;

    @Autowired
    FindLoanService findLoanService;

    @PutMapping("/{idLoan}")
    public Mono<ResponseEntity<LoanDto>> addPayment(@PathVariable String idLoan, @RequestBody PaymentDto paymentDto){

        return findLoanService.findById(idLoan).flatMap(l ->
        {
            l.getPayments().add(paymentDto.paymentDtoToPayment());
            return saveLoanService.save(l);
        }).map(LoanDto::new).map(p -> ResponseEntity
                .created(URI.create("api/v1/payment/"))
                .contentType(MediaType.APPLICATION_JSON)
                .body(p));

    }
}
