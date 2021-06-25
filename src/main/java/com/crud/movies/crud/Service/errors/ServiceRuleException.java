package com.crud.movies.crud.service.errors;

public class ServiceRuleException extends RuntimeException {

    public ServiceRuleException(String message) {
        super(message);
    }
}
