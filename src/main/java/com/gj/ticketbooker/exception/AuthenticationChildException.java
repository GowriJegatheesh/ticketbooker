package com.gj.ticketbooker.exception;

import org.springframework.security.core.AuthenticationException;

/*
This class defines a custom exception which is thrown when user passwords do not match
 */
public class AuthenticationChildException extends AuthenticationException {
    public AuthenticationChildException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
