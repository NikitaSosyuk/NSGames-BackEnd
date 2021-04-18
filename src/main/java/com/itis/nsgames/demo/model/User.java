package com.itis.nsgames.demo.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    private Long id;

    @Column(name = "chat_id")
    private String chatId;

    @Column
    private String username;

    @Column(name = "password_code")
    private String code;

    @Column
    private String email;

    @Column
    private String password;

    @Column(name = "account_state")
    @Enumerated(value = EnumType.STRING)
    private State userState;

    @Column(name = "account_role")
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    @OneToMany(mappedBy = "user")
    private Set<Ad> ads;

    @ManyToMany
    @JoinTable(name = "ad_user",
            joinColumns = {@JoinColumn(name = "ad_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "account_id", referencedColumnName = "id")})
    private Set<Ad> likedAds;

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
