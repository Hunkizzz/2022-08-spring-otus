package ro.otus.education.url.auth.demo.exception;

import javax.naming.AuthenticationException;

public class AuthorityNotFoundException extends AuthenticationException {
    public AuthorityNotFoundException(String msg) {
        super(msg);
    }
}