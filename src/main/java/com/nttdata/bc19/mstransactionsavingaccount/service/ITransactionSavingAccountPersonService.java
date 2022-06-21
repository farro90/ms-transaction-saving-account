package com.nttdata.bc19.mstransactionsavingaccount.service;

import com.nttdata.bc19.mstransactionsavingaccount.model.TransactionSavingAccountPerson;
import com.nttdata.bc19.mstransactionsavingaccount.request.TransactionSavingAccountPersonRequest;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ITransactionSavingAccountPersonService {

    Mono<TransactionSavingAccountPerson> create(TransactionSavingAccountPersonRequest transactionSavingAccountPersonRequest);
    Mono<TransactionSavingAccountPerson> update(TransactionSavingAccountPerson transactionSavingAccountPerson);
    Mono<Void>deleteById(String id);
    Mono<TransactionSavingAccountPerson> findById(String id);
    Flux<TransactionSavingAccountPerson> findAll();

    Flux<TransactionSavingAccountPerson> findByIdSavingAccountPerson(String idSavingAccountPerson);
}
