package gow.generator.utils;

public class BrokenJsonException extends RuntimeException {
    public BrokenJsonException(String message, Throwable cause) {
        super(message, cause);
    }
}
