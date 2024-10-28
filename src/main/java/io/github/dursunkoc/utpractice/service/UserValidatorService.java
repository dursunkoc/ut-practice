package io.github.dursunkoc.utpractice.service;

import io.github.dursunkoc.utpractice.domain.UserValidationRequest;
import io.github.dursunkoc.utpractice.domain.UserValidationResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class UserValidatorService {
    @Value("${validation.service.url:http://user-validation.turkcell-validator.com/validate-user}")
    private String validationServiceUrl;

    public boolean isValid(String username) {
        UserValidationRequest userValidationRequest = new UserValidationRequest(username);
        HttpEntity<UserValidationRequest> requestEntity = new HttpEntity<>(userValidationRequest);

        ResponseEntity<UserValidationResponse> response = new RestTemplate().exchange(validationServiceUrl, HttpMethod.POST, requestEntity, UserValidationResponse.class);
        return response.getBody().isValid();
    }
}
