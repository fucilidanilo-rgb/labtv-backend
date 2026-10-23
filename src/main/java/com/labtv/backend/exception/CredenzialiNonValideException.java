package com.labtv.backend.exception;

public class CredenzialiNonValideException extends RuntimeException {

    public CredenzialiNonValideException() {
        super("Email o password non corrette");
    }
}