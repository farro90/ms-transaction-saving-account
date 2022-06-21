package com.nttdata.bc19.mstransactionsavingaccount.service.impl;

import com.nttdata.bc19.mstransactionsavingaccount.exception.ModelNotFoundException;
import com.nttdata.bc19.mstransactionsavingaccount.model.TransactionSavingAccountPerson;
import com.nttdata.bc19.mstransactionsavingaccount.model.responseWC.SavingAccountPerson;
import com.nttdata.bc19.mstransactionsavingaccount.repository.ITransactionSavingAccountPersonRepository;
import com.nttdata.bc19.mstransactionsavingaccount.request.TransactionSavingAccountPersonRequest;
import com.nttdata.bc19.mstransactionsavingaccount.service.ITransactionSavingAccountPersonService;
import com.nttdata.bc19.mstransactionsavingaccount.util.TransactionTypePasPro;
import com.nttdata.bc19.mstransactionsavingaccount.webclient.impl.ServiceWCImpl;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class TransactionSavingAccountPersonServiceImpl implements ITransactionSavingAccountPersonService {

    @Autowired
    ITransactionSavingAccountPersonRepository transactionSavingAccountPersonRepository;

    @Autowired
    private ServiceWCImpl clientServiceWC;

    @Override
    public Mono<TransactionSavingAccountPerson> create(TransactionSavingAccountPersonRequest transactionSavingAccountPersonRequest) {
        return clientServiceWC.findSavingAccountPersonById(transactionSavingAccountPersonRequest.getIdSavingAccount())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(savingAccountResponse -> this.businessLogicTransaction(transactionSavingAccountPersonRequest, savingAccountResponse));
    }

    @Override
    public Mono<TransactionSavingAccountPerson> update(TransactionSavingAccountPerson transactionSavingAccountPerson) {
        transactionSavingAccountPerson.setUpdatedAt(LocalDateTime.now());
        return clientServiceWC.findSavingAccountPersonById(transactionSavingAccountPerson.getId())
                .switchIfEmpty(Mono.error(new Exception()))
                .flatMap(savingAccountPerson -> this.updateTransaction(transactionSavingAccountPerson));
    }

    @Override
    public Mono<Void> deleteById(String id) {
        return transactionSavingAccountPersonRepository.deleteById(id);
    }

    @Override
    public Mono<TransactionSavingAccountPerson> findById(String id) {
        return transactionSavingAccountPersonRepository.findById(id);
    }

    @Override
    public Flux<TransactionSavingAccountPerson> findAll() {
        return transactionSavingAccountPersonRepository.findAll();
    }

    @Override
    public Flux<TransactionSavingAccountPerson> findByIdSavingAccountPerson(String idSavingAccountPerson) {
        return transactionSavingAccountPersonRepository.findByIdSavingAccountPerson(idSavingAccountPerson);
    }

    private Mono<TransactionSavingAccountPerson> businessLogicTransaction(TransactionSavingAccountPersonRequest transactionSavingAccountPersonRequest, SavingAccountPerson savingAccountPerson){

        TransactionSavingAccountPerson transactionSavingAccountPerson = new TransactionSavingAccountPerson();
        transactionSavingAccountPerson.setId(new ObjectId().toString());
        transactionSavingAccountPerson.setIdSavingAccountPerson(transactionSavingAccountPersonRequest.getIdSavingAccount());
        transactionSavingAccountPerson.setTransactionTypeSavingAccount(transactionSavingAccountPersonRequest.getTransactionTypeSavingAccount());
        transactionSavingAccountPerson.setTransactionDate(LocalDateTime.now());
        transactionSavingAccountPerson.setCreatedAt(LocalDateTime.now());

        if(savingAccountPerson.getLastTrasactionDate().getMonth().getValue() < LocalDateTime.now().getMonth().getValue()){
            savingAccountPerson.setNumberMovements(0);
        }
        savingAccountPerson.setLastTrasactionDate(LocalDateTime.now());

        double commission = 0;
        savingAccountPerson.setNumberMovements(savingAccountPerson.getNumberMovements() + 1);
        if(savingAccountPerson.getPasiveProduct().getNumLimitMovements() < savingAccountPerson.getNumberMovements()){
            commission = savingAccountPerson.getPasiveProduct().getTransactionCommission();
        }

        if(transactionSavingAccountPersonRequest.getTransactionTypeSavingAccount().equals(TransactionTypePasPro.RETIRO.name())){
            if(savingAccountPerson.getAmount() >= transactionSavingAccountPersonRequest.getAmount() + commission){
                savingAccountPerson.setAmount(savingAccountPerson.getAmount() - transactionSavingAccountPersonRequest.getAmount() - commission);
                return clientServiceWC.updateSavingAccountPerson(savingAccountPerson)
                        .switchIfEmpty(Mono.error(new Exception()))
                        .flatMap(savingAccountPersonUpdate -> this.registerTransaction(savingAccountPersonUpdate, transactionSavingAccountPerson));
            }
            else{
                return Mono.error(new ModelNotFoundException("Insufficient balance."));
            }
        }
        else if(transactionSavingAccountPersonRequest.getTransactionTypeSavingAccount().equals(TransactionTypePasPro.DEPOSITO.name())){
            savingAccountPerson.setAmount(savingAccountPerson.getAmount() + transactionSavingAccountPersonRequest.getAmount() - commission);
            return clientServiceWC.updateSavingAccountPerson(savingAccountPerson)
                    .switchIfEmpty(Mono.error(new Exception()))
                    .flatMap(savingAccountPersonUpdate -> this.registerTransaction(savingAccountPersonUpdate, transactionSavingAccountPerson));
        }

        return Mono.error(new ModelNotFoundException("Invalid option"));
    }

    private Mono<TransactionSavingAccountPerson> registerTransaction(SavingAccountPerson savingAccountPerson, TransactionSavingAccountPerson transactionSavingAccountPerson){
        transactionSavingAccountPerson.setSavingAccountPerson(savingAccountPerson);
        return transactionSavingAccountPersonRepository.save(transactionSavingAccountPerson);
    }

    private Mono<TransactionSavingAccountPerson> updateTransaction(TransactionSavingAccountPerson transactionSavingAccountPerson){
        transactionSavingAccountPerson.setUpdatedAt(LocalDateTime.now());
        return transactionSavingAccountPersonRepository.save(transactionSavingAccountPerson);
    }
}
