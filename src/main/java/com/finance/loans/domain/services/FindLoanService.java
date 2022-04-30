package com.finance.loans.domain.services;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.persistence.LoanPersistence;
import com.finance.loans.infrastructure.db.dao.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class FindLoanService {

    @Autowired
    LoanPersistence loanPersistence;

    public Mono<Loan> findById(String id){
        return loanPersistence.findById(id);
    }
}
