package com.finance.loans.infrastructure.db.dao;

import com.finance.loans.domain.model.Loan;
import com.finance.loans.infrastructure.db.entities.LoanEntity;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface LoanRepository extends ReactiveMongoRepository<LoanEntity,String> {
}
