package com.itis.nsgames.demo.controller;

import com.itis.nsgames.demo.dto.user.UserDto;
import com.itis.nsgames.demo.dto.user.UserSignUpForm;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.security.token.jwt.JwtTokenProvider;
import com.itis.nsgames.demo.service.iterfaces.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SignUpController {

    @Autowired
    private UserService userService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping("/signUp")
    public ResponseEntity<?> addTeacher(@RequestBody UserSignUpForm userForm) {

        Map<Object, Object> response = new HashMap<>();
        response.put("user", UserDto.from(userService.saveUser(userForm)));

        User user = userService.getByEmail(userForm.getEmail());
        String token = jwtTokenProvider.createToken(user.getEmail(), user.getUserRole().toString());

        response.put("token", token);
        return ResponseEntity.ok(response);
    }

}
