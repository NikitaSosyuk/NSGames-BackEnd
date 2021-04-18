package com.itis.nsgames.demo.service.userService;

import com.itis.nsgames.demo.dto.user.UserSignUpForm;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserServiceImplementation implements UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserServiceImplementation(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    @Override
    public User getById(Long id) {
        return userRepository.findById(id).orElse(null);
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
    public boolean userIsExist(String email) {
        return userRepository.findUserByEmail(email).isPresent();
    }

    @Override
    public User banUserById(Long id) {
        User user = getById(id);
        if (user != null) {
            user.setUserState(User.State.BANNED);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public User unbanUserById(Long id) {
        User user = getById(id);
        if (user != null) {
            user.setUserState(User.State.ACTIVE);
            userRepository.save(user);
        }
        return user;
    }

    @Override
    public boolean changePassword(String password, String email, String code) {
        User user = userRepository.findUserByEmail(email).orElse(null);
        if (user != null && user.getCode() != null && user.getCode().equals(code)) {
            user.setPassword(passwordEncoder.encode(password));
            user.setCode(null);
            userRepository.save(user);
            return true;
        }
        return false;
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }

    @Override
    public void setCode(String email, String code) {
        User user = userRepository.findUserByEmail(email).orElseThrow(IllegalAccessError::new);
        user.setCode(code);
        userRepository.save(user);
    }
}
