package com.itis.nsgames.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "account", uniqueConstraints = {
        @UniqueConstraint(columnNames = "chat_id", name = "chat_id_unique"),
        @UniqueConstraint(columnNames = "email", name = "email_unique")
})
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "chat_id")
    private String chatId;

    @Column
    private String username;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "user_state")
    @Enumerated(value = EnumType.STRING)
    private State userState;

    @Column(name = "user_role")
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    public enum State {
        ACTIVE, BANNED
    }

    public enum Role {
        USER, ADMIN
    }

    public boolean isActive() {
        return this.userState == State.ACTIVE;
    }

    public boolean isBanned() {
        return this.userState == State.BANNED;
    }

    public boolean isAdmin() {
        return this.userRole == Role.ADMIN;
    }
}
