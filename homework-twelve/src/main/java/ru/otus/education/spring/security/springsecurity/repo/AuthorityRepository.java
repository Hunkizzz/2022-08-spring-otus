package ru.otus.education.spring.security.springsecurity.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.otus.education.spring.security.springsecurity.domain.Authority;

import java.util.List;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    List<Authority> findAllByUsername(String username);
}
