package com.itis.nsgames.demo.dto.user;

import com.itis.nsgames.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private String email;
    private String username;
    private String chatId;

    public static UserDto from(User user) {
        return UserDto.builder()
                .email(user.getEmail())
                .username(user.getUsername())
                .chatId(user.getChatId())
                .build();
    }
}
