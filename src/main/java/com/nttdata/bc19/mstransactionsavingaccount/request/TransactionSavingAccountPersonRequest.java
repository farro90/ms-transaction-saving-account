package com.nttdata.bc19.mstransactionsavingaccount.request;

import lombok.Data;

@Data
public class TransactionSavingAccountPersonRequest {
    private String idSavingAccount;
    private double amount;
    private String transactionTypeSavingAccount;
}
