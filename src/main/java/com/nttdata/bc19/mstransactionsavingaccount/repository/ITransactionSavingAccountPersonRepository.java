package com.nttdata.bc19.mstransactionsavingaccount.repository;

import com.nttdata.bc19.mstransactionsavingaccount.model.TransactionSavingAccountPerson;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface ITransactionSavingAccountPersonRepository extends ReactiveMongoRepository<TransactionSavingAccountPerson, String> {

    Flux<TransactionSavingAccountPerson> findByIdSavingAccountPerson(String idSavingAccountPerson);
}
