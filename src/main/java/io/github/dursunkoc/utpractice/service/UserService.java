package io.github.dursunkoc.utpractice.service;

import io.github.dursunkoc.utpractice.domain.UserWrite;
import io.github.dursunkoc.utpractice.exception.InvalidUserException;
import io.github.dursunkoc.utpractice.repository.UserRepository;
import io.github.dursunkoc.utpractice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidatorService userValidatorService;

    public User createUser(UserWrite userWrite) {
        if (userValidatorService.isValid(userWrite.username())) {
            return User.from(userWrite);
        }
        throw new InvalidUserException("Invalid user");
    }
}
