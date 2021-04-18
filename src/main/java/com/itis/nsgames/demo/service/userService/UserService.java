package com.itis.nsgames.demo.service.userService;

import com.itis.nsgames.demo.dto.user.UserSignUpForm;
import com.itis.nsgames.demo.model.User;

import java.util.List;

public interface UserService {
    List<User> getAll();
    User getById(final Long id);
    User getByEmail(final String email);
    User saveUser(final UserSignUpForm user);
    User banUserById(final Long id);
    User unbanUserById(final Long id);
    void setCode(String email, String code);
    boolean userIsExist(final String email);
    boolean changePassword(final String password, final String email, final String code);
}
