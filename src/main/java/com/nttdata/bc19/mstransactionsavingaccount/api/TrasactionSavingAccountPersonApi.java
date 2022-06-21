package com.nttdata.bc19.mstransactionsavingaccount.api;

import com.nttdata.bc19.mstransactionsavingaccount.model.TransactionSavingAccountPerson;
import com.nttdata.bc19.mstransactionsavingaccount.request.TransactionSavingAccountPersonRequest;
import com.nttdata.bc19.mstransactionsavingaccount.service.ITransactionSavingAccountPersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/transaction/saving")
public class TrasactionSavingAccountPersonApi {

    @Autowired
    private ITransactionSavingAccountPersonService transactionSavingAccountPersonService;

    @PostMapping("/person")
    public Mono<TransactionSavingAccountPerson> create(@RequestBody TransactionSavingAccountPersonRequest transactionSavingAccountPersonRequest){
        return transactionSavingAccountPersonService.create(transactionSavingAccountPersonRequest);
    }

    @PutMapping("/person")
    public Mono<TransactionSavingAccountPerson> update(@RequestBody TransactionSavingAccountPerson transactionSavingAccountPerson){
        return transactionSavingAccountPersonService.update(transactionSavingAccountPerson);
    }

    @GetMapping("/person")
    public Flux<TransactionSavingAccountPerson> findAll(){
        return transactionSavingAccountPersonService.findAll();
    }

    @GetMapping("/person/{id}")
    public Mono<TransactionSavingAccountPerson> findById(@PathVariable String id){ return transactionSavingAccountPersonService.findById(id); }

    @GetMapping("/person/find/{idSavingAcountPerson}")
    public Flux<TransactionSavingAccountPerson> findByIdPasProPerCli(@PathVariable String idSavingAcountPerson){
        return transactionSavingAccountPersonService.findByIdSavingAccountPerson(idSavingAcountPerson);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> delete(@PathVariable String id){
        return transactionSavingAccountPersonService.deleteById(id);
    }
}
