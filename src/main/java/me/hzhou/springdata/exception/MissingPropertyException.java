package me.hzhou.springdata.exception;

public class MissingPropertyException extends RuntimeException {

    public MissingPropertyException(String message) {
        super(message);
    }
}
