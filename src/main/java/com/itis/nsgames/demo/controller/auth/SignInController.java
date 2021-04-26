package com.itis.nsgames.demo.controller.auth;

import com.itis.nsgames.demo.dto.user.UserDto;
import com.itis.nsgames.demo.dto.user.UserSignInForm;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.security.token.jwt.JwtTokenProvider;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
public class SignInController {
    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public SignInController(UserService userService, AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
    }

    @PostMapping("/signIn")
    public ResponseEntity<?> signIn(@RequestBody UserSignInForm userForm) {
        
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userForm.getEmail(),
                        userForm.getPassword()
                ));

        User user = userService.getByEmail(userForm.getEmail());

        String token = jwtTokenProvider.createToken(user.getEmail(), user.getUserRole().toString());

        Map<Object, Object> response = new HashMap<>();
        response.put("user", UserDto.from(user));
        response.put("token", token);
        return ResponseEntity.ok(response);

    }
}
