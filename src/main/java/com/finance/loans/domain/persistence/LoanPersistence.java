package com.finance.loans.domain.persistence;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.domain.model.Payment;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LoanPersistence {

    Flux<Loan> findAll();

    Mono<Loan> save(Loan loan);

    Mono<Loan> findById(String id);

    Mono<Void> delete(Loan loan);
}
