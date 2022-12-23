package com.example.onlinegamingsite.exception;

public class GamingException extends Exception {
    public GamingException(String message) {
        super(message);
    }

    public GamingException(String message, Throwable cause) {
        super(message, cause);
    }
}