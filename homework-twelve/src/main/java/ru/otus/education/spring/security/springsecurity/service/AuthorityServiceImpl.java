package ru.otus.education.spring.security.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.education.spring.security.springsecurity.domain.Authority;
import ru.otus.education.spring.security.springsecurity.exception.AuthorityNotFoundException;
import ru.otus.education.spring.security.springsecurity.repo.AuthorityRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthorityServiceImpl implements AuthorityService {
    private final AuthorityRepository authorityRepository;

    @Override
    public List<Authority> findAllByUsername(String username) throws AuthorityNotFoundException {
        List<Authority> authorities = authorityRepository.findAllByUsername(username);
        if (authorities == null) throw new AuthorityNotFoundException("User`s authorities not found");
        else return authorities;
    }
}
