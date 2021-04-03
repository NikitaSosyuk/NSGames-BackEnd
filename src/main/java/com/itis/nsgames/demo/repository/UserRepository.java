package com.itis.nsgames.demo.repository;

import com.itis.nsgames.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(final String email);
}
