package com.itis.nsgames.demo.controller.auth;

import com.itis.nsgames.demo.dto.user.UserDto;
import com.itis.nsgames.demo.dto.user.UserSignUpForm;
import com.itis.nsgames.demo.security.token.jwt.JwtTokenProvider;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignUpController {
    private final UserService userService;

    public SignUpController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/signUp")
    public ResponseEntity<?> addTeacher(@RequestBody UserSignUpForm userForm) {
        return ResponseEntity.ok(UserDto.from(userService.saveUser(userForm)));
    }
}
