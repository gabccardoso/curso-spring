package com.Spring.demo.services.exceptions;

public class ResourceNotFoundExceptions extends RuntimeException{

    public ResourceNotFoundExceptions(String msg){
        super(msg);
    }
}
