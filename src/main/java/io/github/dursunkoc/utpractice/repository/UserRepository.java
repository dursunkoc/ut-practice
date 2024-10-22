package io.github.dursunkoc.utpractice.repository;

import io.github.dursunkoc.utpractice.domain.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository {
    Optional<User> findByUsername(String username);
    User save(User user);
    User update(User user);
}
