package io.github.dursunkoc.utpractice.domain;

public record UserRead(String username) {
    public static UserRead from(User user) {
        return new UserRead(user.username());
    }
}
