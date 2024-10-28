package io.github.dursunkoc.utpractice.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

@ExtendWith(MockitoExtension.class)
class UserValidatorServiceTest {
    @InjectMocks
    private UserValidatorService userValidatorService;

    private static MockWebServer mockBackEnd;

    @BeforeAll
    static void setUp() throws IOException {
        mockBackEnd = new MockWebServer();
        mockBackEnd.start();
    }

    @BeforeEach
    void setUpEach() {
        String baseUrl = mockBackEnd.url("/").toString();
        ReflectionTestUtils.setField(userValidatorService, "validationServiceUrl", baseUrl);
    }

    @AfterAll
    static void tearDown() throws IOException {
        mockBackEnd.shutdown();
    }

    @ParameterizedTest
    @CsvSource({"true, valid-user", "false, invalid-user"})
    void testIsValidWhenAUsernameProvidedShouldValidateItThroughValidationService(boolean expected, String username) {
        mockBackEnd.enqueue(new MockResponse()
                .setHeader("Content-Type", "application/json")
                .setBody("{\"isValid\": " + expected + "}"));

        boolean result = userValidatorService.isValid(username);
        assertEquals(expected, result);
    }
}
