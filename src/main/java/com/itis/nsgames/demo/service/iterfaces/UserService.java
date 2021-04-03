package com.itis.nsgames.demo.service.iterfaces;

import com.itis.nsgames.demo.dto.user.UserSignUpForm;
import com.itis.nsgames.demo.model.User;

import java.util.List;

public interface UserService {
    User getById(Long id);
    User getByEmail(String email);
    User saveUser(UserSignUpForm user);
    List<User> getAll();
}
