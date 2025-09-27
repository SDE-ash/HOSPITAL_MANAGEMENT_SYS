package com.hms.user.UsreMS.utility;

import java.time.LocalDateTime;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.hms.user.UsreMS.Exceptions.HmsExceptions;

@RestControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorInfo> defaultHandleEzxceptions(Exception e){
        ErrorInfo ex = ErrorInfo.builder()
        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        .errorMessage("SOMETHING BAD HAPPENED")
        .timeStamp(LocalDateTime.now())
        .build();
        return new ResponseEntity<ErrorInfo>(ex, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(HmsExceptions.class)
    public ResponseEntity<ErrorInfo> hmsHandleException( HmsExceptions ex){
        ErrorInfo e=  ErrorInfo.builder()
        .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.toString())
        .errorMessage(ex.getMessage())
        .timeStamp(LocalDateTime.now())
        .build();


        return new ResponseEntity<ErrorInfo>(e, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new LinkedHashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(e -> errors.put(e.getField(), e.getDefaultMessage()));

        return new ResponseEntity<Map<String,String>>(errors, HttpStatus.BAD_REQUEST);
        
    }



}
