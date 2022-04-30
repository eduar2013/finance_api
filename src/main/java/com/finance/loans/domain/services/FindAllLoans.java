package com.finance.loans.domain.services;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.persistence.LoanPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class FindAllLoans {

    @Autowired
    LoanPersistence loanPersistence;

    public Flux<Loan> findAll(){
        return loanPersistence.findAll();
    }
}
