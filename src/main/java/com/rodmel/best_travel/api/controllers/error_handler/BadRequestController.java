package com.rodmel.best_travel.api.controllers.error_handler;

import com.rodmel.best_travel.util.exeptions.IdNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BadRequestController {
    @ExceptionHandler(IdNotFoundException.class)
    public String handleIdNotFound(IdNotFoundException exception){
        return exception.getMessage();
    }
}
