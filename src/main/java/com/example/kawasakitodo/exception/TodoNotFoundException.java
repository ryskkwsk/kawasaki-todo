package com.example.kawasakitodo.exception;

public class TodoNotFoundException extends RuntimeException {

    public TodoNotFoundException() {
        super("対象のTodoは見つかりません。");
    }
}
