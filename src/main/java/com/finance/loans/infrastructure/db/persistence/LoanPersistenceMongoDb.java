package com.finance.loans.infrastructure.db.persistence;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.model.Payment;
import com.finance.loans.domain.persistence.LoanPersistence;
import com.finance.loans.infrastructure.db.dao.LoanRepository;
import com.finance.loans.infrastructure.db.entities.LoanEntity;
import com.finance.loans.infrastructure.db.entities.PaymentEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class LoanPersistenceMongoDb implements LoanPersistence {

    @Autowired
    LoanRepository loanRepository;

    @Override
    public Flux<Loan> findAll() {
        return loanRepository.findAll().map(LoanEntity::toLoan);
    }

    @Override
    public Mono<Loan> save(Loan loan) {
        LoanEntity loanEntity = new LoanEntity(loan);
        return loanRepository.save(loanEntity).map(LoanEntity::toLoan);
    }

    @Override
    public Mono<Loan> findById(String id) {
        return loanRepository.findById(id).map(LoanEntity::toLoan);
    }

}
