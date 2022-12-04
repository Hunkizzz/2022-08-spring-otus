package ru.otus.education.spring.security.springsecurity.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.otus.education.spring.security.springsecurity.domain.User;
import ru.otus.education.spring.security.springsecurity.repo.UserRepository;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public User findByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s);
        if (user == null) throw new UsernameNotFoundException("User not found");
        else return user;
    }
}