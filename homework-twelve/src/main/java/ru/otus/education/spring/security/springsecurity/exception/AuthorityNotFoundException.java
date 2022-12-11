package ru.otus.education.spring.security.springsecurity.exception;

import javax.naming.AuthenticationException;

public class AuthorityNotFoundException extends AuthenticationException {
    public AuthorityNotFoundException(String msg) {
        super(msg);
    }
}