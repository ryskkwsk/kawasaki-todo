package com.example.kawasakitodo.exception;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException(String mesaage) {
        super(mesaage);
    }
}
