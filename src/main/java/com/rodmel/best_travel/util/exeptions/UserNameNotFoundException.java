package com.rodmel.best_travel.util.exeptions;

public class UserNameNotFoundException extends RuntimeException{

    private static  final String ERROR_MESSAGE = "User no exist in %s";

    public UserNameNotFoundException(String tableName){
        super (String.format(ERROR_MESSAGE,tableName));
    }
}
