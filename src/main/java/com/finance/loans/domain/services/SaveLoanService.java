package com.finance.loans.domain.services;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.persistence.LoanPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class SaveLoanService {

    @Autowired
    LoanPersistence loanPersistence;

    public Mono<Loan> save(Loan loan){
        return loanPersistence.save(loan);
    }
}
