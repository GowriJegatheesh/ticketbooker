package com.gj.ticketbooker.exception;

import org.springframework.security.core.AuthenticationException;

public class AuthenticationChildException extends AuthenticationException {
    public AuthenticationChildException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
