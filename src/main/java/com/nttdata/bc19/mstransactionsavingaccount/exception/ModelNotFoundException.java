package com.nttdata.bc19.mstransactionsavingaccount.exception;

//@ResponseStatus(HttpStatus.NOT_FOUND)
public class ModelNotFoundException extends RuntimeException{

    public ModelNotFoundException(String message){
        super(message);
    }
}
