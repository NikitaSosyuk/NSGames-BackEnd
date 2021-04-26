package com.itis.nsgames.demo.service.offerService;

import com.itis.nsgames.demo.dto.offer.OfferDao;
import com.itis.nsgames.demo.model.Ad;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.Offer;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.repository.AdRepository;
import com.itis.nsgames.demo.repository.OfferRepository;
import com.itis.nsgames.demo.service.adService.AdService;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImplementation implements OfferService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final OfferRepository offerRepository;

    public OfferServiceImplementation(OfferRepository offerRepository, UserService userService, AdRepository adRepository) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.adRepository = adRepository;
    }

    @Override
    public List<Game> getGameList(Integer id) {
        return offerRepository.findById(id).orElseThrow(IllegalAccessError::new).getTradeGames().stream().sorted().collect(Collectors.toList());
    }

    @Override
    public boolean createOffer(OfferDao offerDao, Long userId) {
        User user = userService.getById(userId);
        Ad ad = adRepository.findById(offerDao.getAdId()).orElseThrow(IllegalAccessError::new);
        //Offer offer = Offer.build
        return false;
    }
}
