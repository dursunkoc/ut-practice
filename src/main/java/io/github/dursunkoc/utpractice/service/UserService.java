package io.github.dursunkoc.utpractice.service;

import io.github.dursunkoc.utpractice.domain.UserWrite;
import io.github.dursunkoc.utpractice.exception.InvalidUserException;
import io.github.dursunkoc.utpractice.repository.UserRepository;
import io.github.dursunkoc.utpractice.domain.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final UserValidatorService userValidatorService;

    public User createUser(UserWrite userWrite) {
        if (!userValidatorService.isValid(userWrite.username())) {
            throw new InvalidUserException("Invalid user");
        }
        Optional<User> existingUserOpt = userRepository.findByUsername(userWrite.username());
        if (existingUserOpt.isPresent()) {
            User user = existingUserOpt.get();
            var updated = user.updateFrom(userWrite);
            return userRepository.update(updated);
        }else{
            return userRepository.save(User.from(userWrite));
        }
    }
}
