package com.itis.nsgames.demo.service.offerService;

import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.dto.offer.OfferDto;
import com.itis.nsgames.demo.model.Ad;
import com.itis.nsgames.demo.model.Offer;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.repository.AdRepository;
import com.itis.nsgames.demo.repository.OfferRepository;
import com.itis.nsgames.demo.service.gameService.GameService;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImplementation implements OfferService {
    private final AdRepository adRepository;
    private final UserService userService;
    private final OfferRepository offerRepository;
    private final GameService gameService;

    public OfferServiceImplementation(OfferRepository offerRepository, UserService userService, AdRepository adRepository, GameService gameService) {
        this.offerRepository = offerRepository;
        this.userService = userService;
        this.adRepository = adRepository;
        this.gameService = gameService;
    }

    @Override
    public List<GameDto> getGameList(Integer id) {
        return offerRepository.findById(id).orElseThrow(IllegalAccessError::new).getTradeGames().stream().sorted().map(GameDto::from).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> getOffersById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(IllegalAccessError::new);
        return ad.getOfferList().stream().sorted().map(x -> OfferDto.from(x, x.getFromUser().getUsername())).collect(Collectors.toList());
    }

    @Override
    public boolean createOffer(OfferDto offerDto, Long userId) {
        User user = userService.getById(userId);
        Ad ad = adRepository.findById(offerDto.getAdId()).orElseThrow(IllegalAccessError::new);
        Offer offer = Offer.builder()
                .date(new Date())
                .fromUser(user)
                .price(offerDto.getPrice())
                .description(offerDto.getDescription())
                .tradeGames(gameService.getGamesByIds(offerDto.getTradeList()))
                .build();
        List<Offer> offerList = ad.getOfferList();
        offerList.add(offer);
        offerRepository.save(offer);
        adRepository.save(ad);
        return true;
    }
}
