package com.nttdata.bc19.mstransactionsavingaccount.model;

import com.nttdata.bc19.mstransactionsavingaccount.model.responseWC.SavingAccountPerson;
import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class TransactionSavingAccountPerson extends BaseModel{
    //private  String code;
    private String idSavingAccountPerson;
    private SavingAccountPerson savingAccountPerson;
    private LocalDateTime transactionDate;
    private String transactionTypeSavingAccount;
    private double amount;
}
