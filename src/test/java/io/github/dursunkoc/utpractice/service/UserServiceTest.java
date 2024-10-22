package io.github.dursunkoc.utpractice.service;

import io.github.dursunkoc.utpractice.domain.User;
import io.github.dursunkoc.utpractice.domain.UserWrite;
import io.github.dursunkoc.utpractice.exception.InvalidUserException;
import io.github.dursunkoc.utpractice.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @InjectMocks
    UserService userService;
    @Mock
    UserRepository userRepository;
    @Mock
    UserValidatorService userValidatorService;

    private static UserWrite validRequest() {
        String username = "dursunkoc";
        String first = "Dursun";
        String last = "Koc";
        return new UserWrite(username, first, last);
    }

    private static UserWrite inValidRequest() {
        String username = "invalid-user";
        String first = "Dursun";
        String last = "Koc";
        return new UserWrite(username, first, last);
    }

    @BeforeEach
    void setUp() {
    }


    @Test
    void testCreateUserWhenRequestIsValidShouldReturnNonNull() {
        UserWrite userWrite = validRequest();
        when(userValidatorService.isValid(argThat(s -> s!=null && !s.contains("invalid")))).thenReturn(true);

        User user = userService.createUser(userWrite);

        assertNotNull(user);
    }

    @Test
    void testCreateUserWhenRequestIsInvalidShouldThrowInvalidUserException() {
        UserWrite userWrite = inValidRequest();
        when(userValidatorService.isValid(argThat(s -> s.contains("invalid")))).thenReturn(false);

        assertThrows(InvalidUserException.class, () -> userService.createUser(userWrite), "Invalid user");
    }

    @Test
    void testCreateUserWhenUserExistsShouldUpdateExisting() {
        // Arrange
        final var userWrite = validRequest();
        final var ex = "-ex";
        final var user = new User(userWrite.username(), userWrite.first() + ex, userWrite.last() + ex);
        when(userRepository.findByUsername(userWrite.username())).thenReturn(Optional.of(user));
        when(userValidatorService.isValid(argThat(s -> s!=null && !s.contains("invalid")))).thenReturn(true);
        when(userRepository.update(any())).thenAnswer(AdditionalAnswers.returnsFirstArg());
        // Act
        final var updatedUser = userService.createUser(userWrite);
        // Assert
        assertNotNull(updatedUser);
        verify(userRepository, times(1)).update(argThat(u ->
                u.first().equals(userWrite.first()) &&
                        u.last().equals(userWrite.last())));
    }

    @Test
    void testCreateUserWhenUserDoesNotExistShouldCreateNew() {
        // Arrange
        final var userWrite = validRequest();
        when(userRepository.findByUsername(userWrite.username())).thenReturn(Optional.empty());
        when(userValidatorService.isValid(argThat(s -> s!=null && !s.contains("invalid")))).thenReturn(true);
        // Act
        final var user = userService.createUser(userWrite);
        // Assert
        assertNotNull(user);
        verify(userRepository, times(1)).save(any());
    }

}