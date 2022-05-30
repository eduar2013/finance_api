package com.finance.loans.domain.services;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.persistence.LoanPersistence;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class DeleteLoanService {

    @Autowired
    LoanPersistence loanPersistence;

    public Mono<Void> deleteLoan(Loan loan){
        return loanPersistence.delete(loan);
    }
}
