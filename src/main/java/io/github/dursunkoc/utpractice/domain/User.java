package io.github.dursunkoc.utpractice.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String username;
    private String first;
    private String last;

    public static User from(UserWrite userWrite) {
        return User.builder()
                .username(userWrite.username())
                .first(userWrite.first())
                .last(userWrite.last())
                .build();
    }

    public User updateFrom(UserWrite userWrite) {
        return User.builder()
                .id(this.id)
                .first(userWrite.first())
                .last(userWrite.last())
                .username(userWrite.username())
                .build();
    }

    public User(String username, String first, String last) {
        this.username = username;
        this.first = first;
        this.last = last;
    }

    public String username() {
        return this.username;
    }

    public String first() {
        return this.first;
    }

    public String last() {
        return this.last;
    }
}
