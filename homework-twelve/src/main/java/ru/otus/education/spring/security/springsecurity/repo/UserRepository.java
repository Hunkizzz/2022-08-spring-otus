package ru.otus.education.spring.security.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.spring.security.springsecurity.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
