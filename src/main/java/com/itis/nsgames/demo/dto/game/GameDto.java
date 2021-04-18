package com.itis.nsgames.demo.dto.game;

import com.itis.nsgames.demo.model.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GameDto {
    private Integer id;
    private String name;

    public static GameDto from(Game game) {
        return GameDto.builder()
                .id(game.getId())
                .name(game.getName())
                .build();
    }
}
