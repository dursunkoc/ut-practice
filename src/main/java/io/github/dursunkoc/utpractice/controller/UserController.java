package io.github.dursunkoc.utpractice.controller;

import io.github.dursunkoc.utpractice.domain.User;
import io.github.dursunkoc.utpractice.domain.UserRead;
import io.github.dursunkoc.utpractice.domain.UserWrite;
import io.github.dursunkoc.utpractice.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @PostMapping
    public UserRead createUser(@RequestBody UserWrite userWrite) {
        User user = userService.createUser(userWrite);
        return UserRead.from(user);
    }
}
