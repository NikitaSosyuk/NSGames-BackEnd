package com.itis.nsgames.demo.dto.ad;

import com.itis.nsgames.demo.model.Ad;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.PhotoName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AdDto {
    private Integer id;
    private String title;
    private String description;
    private Double price;
    private Date date;
    private String username;
    private String chatId;
    private Integer countTradeGames;
    private Integer countViews;
    private List<String> photoNames;

    public static AdDto from(Ad ad, String username, String chatId, List<String> photoNames) {
        return AdDto.builder()
                .id(ad.getId())
                .title(ad.getTitle())
                .description(ad.getDescription())
                .price(ad.getPrice())
                .username(username)
                .countTradeGames(ad.getTradeGames().size())
                .date(ad.getDate())
                .chatId(chatId)
                .countViews(ad.getViews())
                .photoNames(photoNames)
                .build();
    }
}
