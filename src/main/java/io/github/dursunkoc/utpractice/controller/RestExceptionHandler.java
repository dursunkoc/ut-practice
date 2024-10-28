package io.github.dursunkoc.utpractice.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ServerWebExchange;

@RestControllerAdvice
@Slf4j
public class RestExceptionHandler {

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.OK)
    ExceptionMessage generalException(ServerWebExchange exchange, Exception ex) {
        log.error("System exception: ", ex);
        String id = exchange.getRequest().getId();
        return ExceptionMessage.builder()
                .error(true)
                .trxId(id)
                .message("System error")
                .build();
    }
}
