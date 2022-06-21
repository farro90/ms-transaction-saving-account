package com.nttdata.bc19.mstransactionsavingaccount.webclient;

import com.nttdata.bc19.mstransactionsavingaccount.model.responseWC.*;
import reactor.core.publisher.Mono;

public interface IServiceWC {
    Mono<CurrentAccountPerson> findCurrentAccountPersonById(String id);
    Mono<SavingAccountPerson> findSavingAccountPersonById(String id);
    Mono<FixedTermAccountPerson> findFixedTermAccountPersonById(String id);
    Mono<CurrentAccountBusiness> findCurrentAccountBusinessById(String id);

    Mono<CreditPerson> findCreditPersonById(String id);
    Mono<CreditCardPerson> findCreditCardPersonById(String id);
    Mono<CreditBusiness> findCreditBusinessById(String id);
    Mono<CreditCardBusiness> findCreditCardBusinessById(String id);

    Mono<CurrentAccountPerson> updateCurrentAccountPerson(CurrentAccountPerson currentAccountPerson);
    Mono<SavingAccountPerson> updateSavingAccountPerson(SavingAccountPerson savingAccountPerson);
    Mono<FixedTermAccountPerson> updateFixedTermAccountPerson(FixedTermAccountPerson fixedTermAccountPerson);
    Mono<CurrentAccountBusiness> updateCurrentAccountBusiness(CurrentAccountBusiness currentAccountBusiness);
    Mono<CreditPerson> updateCreditPerson(CreditPerson creditPerson);
    Mono<CreditBusiness> updateCreditBusiness(CreditBusiness creditBusiness);
    Mono<CreditCardPerson> updateCreditCardPerson(CreditCardPerson creditCardPerson);
    Mono<CreditCardBusiness> updateCreditCardBusiness(CreditCardBusiness creditCardBusiness);
}
