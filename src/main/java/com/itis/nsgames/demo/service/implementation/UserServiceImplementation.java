package com.itis.nsgames.demo.service.implementation;

import com.itis.nsgames.demo.dto.user.UserSignUpForm;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.repository.UserRepository;
import com.itis.nsgames.demo.service.iterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow(IllegalAccessError::new);
    }

    @Override
    public User saveUser(UserSignUpForm user) {
        return userRepository.save(User
                .builder()
                .username(user.getUsername())
                .password(passwordEncoder.encode(user.getPassword()))
                .email(user.getEmail())
                .chatId(UUID.randomUUID().toString())
                .userRole(User.Role.USER)
                .userState(User.State.ACTIVE)
                .build());
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
