package io.github.dursunkoc.utpractice.repository;

import io.github.dursunkoc.utpractice.domain.User;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository{
    Optional<User> findByUsername(String username);

    User save(User user);

    User update(User user);
}
