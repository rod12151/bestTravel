package com.rodmel.best_travel.util.exeptions;

public class ForbiddenCustomerException extends RuntimeException{
    public ForbiddenCustomerException(){
        super("This Customer is blocked");
    }
}
