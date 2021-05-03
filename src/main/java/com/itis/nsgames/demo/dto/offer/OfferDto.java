package com.itis.nsgames.demo.dto.offer;

import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.Offer;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDto {
    private Integer id;
    private String name;
    private Integer adId;
    private List<Integer> tradeList;
    private Integer countOfTradeList;
    private Double price;
    private String description;
    private String chatId;

    public static OfferDto from(Offer offer, String username) {
        return OfferDto.builder()
                .id(offer.getId())
                .name(username)
                .description(offer.getDescription())
                .price(offer.getPrice())
                .chatId(offer.getFromUser().getChatId())
                .countOfTradeList(offer.getTradeGames().size() == 0? null: offer.getTradeGames().size())
                .build();
    }
}
