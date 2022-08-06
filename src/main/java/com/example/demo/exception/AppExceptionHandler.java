package com.example.demo.exception;

import org.apache.tomcat.jni.User;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;
import java.util.HashMap;

@ControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(value = UserServiceException.class)
    public ResponseEntity<Object> handleUserServiceException (UserServiceException userServiceException){



        ExceptionMessage exceptionMessage = new ExceptionMessage(userServiceException.getMessage(),new Date());
        return  new ResponseEntity<>(exceptionMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<Object> handleOtherException (Exception exception){

          ExceptionMessage exceptionMessage = new ExceptionMessage(exception.getMessage(),new Date());
          return  new ResponseEntity<>(exceptionMessage,new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);

    }

}
