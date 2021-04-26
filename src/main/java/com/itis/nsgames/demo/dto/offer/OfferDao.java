package com.itis.nsgames.demo.dto.offer;

import com.itis.nsgames.demo.model.Game;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OfferDao {
    private Integer adId;
    private List<Game> tradeList;
    private Double price;
}
