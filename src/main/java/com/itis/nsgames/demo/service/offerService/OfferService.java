package com.itis.nsgames.demo.service.offerService;

import com.itis.nsgames.demo.dto.offer.OfferDao;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.Offer;

import java.util.List;

public interface OfferService {
    List<Game> getGameList(Integer id);
    boolean createOffer(OfferDao offer, Long userId);
}
