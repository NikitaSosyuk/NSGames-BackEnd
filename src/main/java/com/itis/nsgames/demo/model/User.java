package com.itis.nsgames.demo.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

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
    @Setter @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Setter @Getter
    @Column(name = "chat_id")
    private String chatId;

    @Column
    @Setter @Getter
    private String username;

    @Setter @Getter
    @Column(name = "password_code")
    private String code;

    @Column
    @Setter @Getter
    private String email;

    @Column
    @Setter @Getter
    private String password;

    @Setter @Getter
    @Column(name = "account_state")
    @Enumerated(value = EnumType.STRING)
    private State userState;

    @Setter @Getter
    @Column(name = "account_role")
    @Enumerated(value = EnumType.STRING)
    private Role userRole;

    @Setter @Getter
    @OneToMany(mappedBy = "user")
    private List<Ad> ads;

    @Setter @Getter
    @ManyToMany
    @JoinTable(name = "ad_user",
            joinColumns = {@JoinColumn(name = "ad_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "accoun_id", referencedColumnName = "id")})
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
