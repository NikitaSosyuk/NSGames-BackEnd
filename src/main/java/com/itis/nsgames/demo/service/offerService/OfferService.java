package com.itis.nsgames.demo.service.offerService;

import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.dto.offer.OfferDto;
import com.itis.nsgames.demo.model.Game;

import java.util.List;

public interface OfferService {
    List<GameDto> getGameList(Integer id);
    List<OfferDto> getOffersById(Integer id);
    boolean createOffer(OfferDto offer, Long userId);
}
