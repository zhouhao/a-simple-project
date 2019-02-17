package me.hzhou.todo.exception;

public class MissingPropertyException extends RuntimeException {

    public MissingPropertyException(String message) {
        super(message);
    }
}
