package org.example.locale.exception;

public class InvalidLocaleNumberException extends Exception {
    public InvalidLocaleNumberException(String message) {
        super(message);
    }

    public InvalidLocaleNumberException(String message, Throwable cause) {
        super(message, cause);
    }
}
