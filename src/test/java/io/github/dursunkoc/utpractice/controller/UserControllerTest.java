package io.github.dursunkoc.utpractice.controller;

import static org.mockito.Mockito.when;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.dursunkoc.utpractice.domain.User;
import io.github.dursunkoc.utpractice.domain.UserWrite;
import io.github.dursunkoc.utpractice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@ExtendWith(MockitoExtension.class)
@WebFluxTest(controllers = UserController.class, properties = "spring.main.banner-mode=off")
class UserControllerTest {
    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private UserService userService;

    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void testCreateUserWhenUserCreatedShouldReturnTheUsername() throws JsonProcessingException {
        UserWrite userWrite = new UserWrite("test", "first-name", "last-name");
        String request = objectMapper.writeValueAsString(userWrite);

        when(userService.createUser(userWrite)).thenReturn(User.from(userWrite));

        webTestClient
                .post()
                .uri("/user")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.username")
                .isEqualTo("test");
    }

    @Test
    void testCreateUserWhenUserCreationFailsShouldReturnErrorMessage() throws JsonProcessingException {
        UserWrite userWrite = new UserWrite("test", "first-name", "last-name");
        String request = objectMapper.writeValueAsString(userWrite);

        when(userService.createUser(userWrite)).thenThrow(new RuntimeException("Test Exception"));

        webTestClient
                .post()
                .uri("/user")
                .body(BodyInserters.fromValue(request))
                .exchange()
                .expectStatus()
                .isOk()
                .expectBody()
                .jsonPath("$.error")
                .isEqualTo("true")
                .jsonPath("$.message")
                .isEqualTo("System error")
                .jsonPath("$.trxId")
                .exists()
                .jsonPath("$.username")
                .doesNotExist();
    }
}
