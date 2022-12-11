package ro.otus.education.url.auth.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ro.otus.education.url.auth.demo.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
