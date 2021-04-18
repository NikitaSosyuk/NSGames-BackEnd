package com.itis.nsgames.demo.controller.auth;

import com.itis.nsgames.demo.dto.user.UserChangePasswordForm;
import com.itis.nsgames.demo.dto.user.UserDto;
import com.itis.nsgames.demo.dto.user.UserSignInForm;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.security.token.jwt.JwtTokenProvider;
import com.itis.nsgames.demo.service.mailService.MailService;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class ForgotPasswordController {
    @Autowired
    private UserService userService;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private MailService mailService;

    @PostMapping("/forgotPassword/sendEmail")
    public ResponseEntity<?> signIn(@RequestBody UserSignInForm userForm) {
        if (mailService.sendCodeToTheMail(userForm.getEmail())) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(440).body(null);
    }

    @PostMapping("/forgotPassword/changePassword")
    public ResponseEntity<?> signIn(@RequestBody UserChangePasswordForm userForm) {
        if (userService.changePassword(userForm.getPassword(), userForm.getEmail(), userForm.getCode())) {
            User user = userService.getByEmail(userForm.getEmail());
            String token = jwtTokenProvider.createToken(user.getEmail(), user.getUserRole().toString());

            Map<Object, Object> response = new HashMap<>();
            response.put("token", token);
            response.put("user", UserDto.from(user));
            return ResponseEntity.ok(response);
        }
        return ResponseEntity.status(440).body(null);
    }
}
