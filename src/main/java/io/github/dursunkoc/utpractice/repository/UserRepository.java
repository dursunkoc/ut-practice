package io.github.dursunkoc.utpractice.repository;

import io.github.dursunkoc.utpractice.domain.User;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    Optional<User> findByUsername(String username);

    //    User save(User user);

    default User update(User user) {
        return save(user);
    }
}
