package com.finance.loans.infrastructure.rest;

import com.finance.loans.domain.services.DeleteLoanService;
import com.finance.loans.domain.services.FindAllLoans;
import com.finance.loans.domain.services.FindLoanService;
import com.finance.loans.domain.services.SaveLoanService;
import com.finance.loans.infrastructure.rest.dto.LoanDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/finance/api/v1/loan")
@CrossOrigin(origins = "http://localhost:4200")
public class LoanResource {

    @Autowired
    FindAllLoans findAllLoans;

    @GetMapping
    public Flux<LoanDto> findAll(){
        return findAllLoans.findAll().map(LoanDto::new);
    }

    @Autowired
    SaveLoanService saveLoanService;

    @Autowired
    FindLoanService findLoanService;

    @GetMapping("/{id}")
    public Mono<ResponseEntity<LoanDto>> findById(@PathVariable String id){
        return findLoanService.findById(id).map(LoanDto::new).map(
                p -> ResponseEntity.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p))
                        .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<LoanDto>> create(@RequestBody LoanDto loanDto){
        loanDto.setLastUpdateDate(LocalDateTime.now());
        return  saveLoanService.save(loanDto.loanDtoToLoan()).map(LoanDto::new).map(
                p -> ResponseEntity
                        .created(URI.create("api/v1/loan/".concat(p.getId())))
                        .contentType(MediaType.APPLICATION_JSON)
                        .body(p));}


    @Autowired
    DeleteLoanService deleteLoanService;

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> delete(@PathVariable String id){
       return findLoanService.findById(id).flatMap( p ->{
           return deleteLoanService.deleteLoan(p).then(Mono.just(new ResponseEntity<Void>(HttpStatus.NO_CONTENT)));
       }).defaultIfEmpty(new ResponseEntity<Void>(HttpStatus.NOT_FOUND));
    }
}