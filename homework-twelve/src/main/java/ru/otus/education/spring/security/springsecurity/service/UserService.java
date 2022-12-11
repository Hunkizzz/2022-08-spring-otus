package ru.otus.education.spring.security.springsecurity.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.otus.education.spring.security.springsecurity.domain.User;

public interface UserService {
    User findByUsername(String s) throws UsernameNotFoundException;
}
