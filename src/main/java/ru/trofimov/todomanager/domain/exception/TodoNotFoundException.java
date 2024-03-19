package ru.trofimov.todomanager.domain.exception;

import javax.persistence.EntityNotFoundException;

public class TodoNotFoundException extends EntityNotFoundException {

    public TodoNotFoundException(String message) {
        super(message);
    }
}
