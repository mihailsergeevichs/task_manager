package com.mihailsergeevichs.todo.manager.user.service;

public class DuplicateEmailException extends Exception {

    public DuplicateEmailException(String message) {
        super(message);
    }
}
