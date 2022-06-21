package com.nttdata.bc19.mstransactionsavingaccount.model.responseWC;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data
@Document
public class SavingAccountPerson extends BaseModel{

    private double amount;
    private String accountNumber;
    private LocalDateTime LastTrasactionDate;
    private String idPersonClient;
    private String idPasiveProduct;
    private PersonClient personClient;
    private PasiveProduct pasiveProduct;
    private int numberMovements;
}
