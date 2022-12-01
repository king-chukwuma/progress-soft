package com.chukwuma.progresssoft.exception;

import com.chukwuma.progresssoft.dto.GenericExceptionResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
@Slf4j
public class CustomExceptionHandler{

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericExceptionResponse handleIllegalArgumentException(BadRequestException e){
        return new GenericExceptionResponse(400, false, e.getMessage(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericExceptionResponse handleGenericException(Exception e){
        return new GenericExceptionResponse(HttpStatus.BAD_REQUEST.value(), false, e.getMessage(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public GenericExceptionResponse handleMethodArgException(MethodArgumentNotValidException e){
        return new GenericExceptionResponse(e.getStatusCode().value(), false, e.getAllErrors().get(e.getErrorCount() -1).getDefaultMessage(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public GenericExceptionResponse handleNotFOundException(NotFoundException e){
        return new GenericExceptionResponse(HttpStatus.NOT_FOUND.value(), false, e.getMessage(), Timestamp.valueOf(LocalDateTime.now()));
    }
}
