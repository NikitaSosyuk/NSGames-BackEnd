package com.itis.nsgames.demo.service.adService;

import com.itis.nsgames.demo.dto.ad.AdCreateForm;
import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.dto.ad.AdFeedDto;
import com.itis.nsgames.demo.model.Ad;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.repository.AdRepository;
import com.itis.nsgames.demo.repository.UserRepository;
import com.itis.nsgames.demo.service.gameService.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdServiceImplementation implements AdService {
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final GameService gameService;

    public AdServiceImplementation(GameService gameService, AdRepository adRepository, UserRepository userRepository) {
        this.gameService = gameService;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<AdFeedDto> getFeed(Long userId) {
        Set<Ad> userLikedAd = userRepository.findById(userId).orElseThrow(IllegalAccessError::new).getLikedAds();
        Random random = new Random();
        long count = adRepository.count();
        List<AdFeedDto> result = new LinkedList<>();
        for (int i = 0; i < 30; i++) {
            Ad ad = adRepository.findById(random.nextInt((int) count)).orElseThrow(IllegalAccessError::new);
            result.add(AdFeedDto.from(ad, userLikedAd.contains(ad)));
        }
        return result;
    }

    @Override
    public AdDto saveAd(AdCreateForm adForm) {
        User user = userRepository.findById(adForm.getUserId()).orElseThrow(IllegalAccessError::new);
        Ad ad = Ad.builder()
                .title(adForm.getTitle())
                .description(adForm.getDescription())
                .price(adForm.getPrice())
                .tradeGames(gameService.getGamesByIds(adForm.getGames()))
                .date(new Date())
                .adState(Ad.State.ACTIVE)
                .user(user)
                .build();
        user.getAds().add(ad);
        return AdDto.from(adRepository.save(ad), userRepository.save(user).getUsername());
    }

    @Override
    public boolean likeAd(Long userId, Integer adId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        Ad ad = adRepository.findById(adId).orElseThrow(IllegalAccessError::new);
        Set<Ad> ads = user.getLikedAds();
        if (ads.contains(ad)) {
            ads.remove(ad);
            return true;
        }
        ads.add(ad);
        userRepository.save(user);
        return true;
    }

    @Override
    public AdDto getAdById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(IllegalAccessError::new);
        User user = ad.getUser();
        return AdDto.from(ad, user.getUsername());
    }

    @Override
    public List<AdFeedDto> getFavorites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        return user.getLikedAds().stream().map(x -> AdFeedDto.from(x, true)).collect(Collectors.toList());
    }
}
