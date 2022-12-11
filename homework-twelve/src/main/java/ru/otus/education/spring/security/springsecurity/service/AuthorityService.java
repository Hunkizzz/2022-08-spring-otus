package ru.otus.education.spring.security.springsecurity.service;

import ru.otus.education.spring.security.springsecurity.domain.Authority;
import ru.otus.education.spring.security.springsecurity.exception.AuthorityNotFoundException;

import java.util.List;

public interface AuthorityService {
    List<Authority> findAllByUsername(String username) throws AuthorityNotFoundException;
}
