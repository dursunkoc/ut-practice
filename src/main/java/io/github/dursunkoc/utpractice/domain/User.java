package io.github.dursunkoc.utpractice.domain;
public record User(String username, String first, String last) {
    public static User from(UserWrite userWrite) {
        return new User(userWrite.username(), userWrite.first(), userWrite.last());
    }
}
