package com.Spring.demo.controllers.handler;

import com.Spring.demo.DTO.CustomError;
import com.Spring.demo.DTO.ValidationError;
import com.Spring.demo.services.exceptions.ResourceNotFoundExceptions;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

@ControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(ResourceNotFoundExceptions.class)
    public ResponseEntity<CustomError> resourceNotFound(ResourceNotFoundExceptions e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
        CustomError error = new CustomError(Instant.now(), httpStatus.value(), e.getMessage(), request.getRequestURI());
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<CustomError> methodArgumentNotValidation(MethodArgumentNotValidException e, HttpServletRequest request){
        HttpStatus httpStatus = HttpStatus.UNPROCESSABLE_ENTITY;

        ValidationError error = new ValidationError(Instant.now(), httpStatus.value(), e.getMessage(), request.getRequestURI());
        for(FieldError f : e.getBindingResult().getFieldErrors()){
            error.addError(f.getField(), f.getDefaultMessage());
        }
        return ResponseEntity.status(httpStatus).body(error);
    }


}
