package com.itis.nsgames.demo.dto.user;

import com.itis.nsgames.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSignUpForm {
    private String email;
    private String username;
    private String password;
}
