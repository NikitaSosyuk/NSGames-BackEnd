package com.itis.nsgames.demo.service.adService;

import com.itis.nsgames.demo.dto.ad.AdCreateForm;
import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.dto.ad.AdFeedDto;
import com.itis.nsgames.demo.dto.ad.AdIdForm;
import com.itis.nsgames.demo.model.Ad;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.model.PhotoName;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.repository.AdRepository;
import com.itis.nsgames.demo.repository.PhotoRepository;
import com.itis.nsgames.demo.repository.UserRepository;
import com.itis.nsgames.demo.service.gameService.GameService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class AdServiceImplementation implements AdService {
    private final UserRepository userRepository;
    private final AdRepository adRepository;
    private final GameService gameService;
    private final PhotoRepository photoRepository;

    public AdServiceImplementation(GameService gameService, AdRepository adRepository, UserRepository userRepository, PhotoRepository photoRepository) {
        this.gameService = gameService;
        this.adRepository = adRepository;
        this.userRepository = userRepository;
        this.photoRepository = photoRepository;
    }

    @Override
    public Set<AdFeedDto> getFeed(Long userId) {
        Set<Ad> userLikedAd = userRepository.findById(userId).orElseThrow(IllegalAccessError::new).getLikedAds();
        Random random = new Random();
        long count = adRepository.count();
        Set<AdFeedDto> result = new HashSet<>();
        for (int i = 0; i < 30; i++) {
            int index = random.nextInt((int) count + 1);
            if (index >= 0) {
                adRepository.findById(index).ifPresent(ad -> result.add(AdFeedDto.from(ad, userLikedAd.contains(ad))));
            }
        }
        return result;
    }

    @Override
    public List<Game> getTradeList(Integer id) {
        return adRepository.findById(id).orElseThrow(IllegalAccessError::new).getTradeGames();
    }

    @Override
    public AdIdForm saveAd(Long userId, AdCreateForm adForm) {

        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);

        Ad ad = Ad.builder()
                .title(adForm.getTitle())
                .description(adForm.getDescription())
                .price(adForm.getPrice())
                .tradeGames(gameService.getGamesByIds(adForm.getGames()))
                .date(new Date())
                .user(user)
                .views(1)
                .build();

        user.getAds().add(ad);
        userRepository.save(user);

        return AdIdForm.builder().id(adRepository.save(ad).getId()).build();
    }

    @Override
    public boolean saveAdPhoto(Integer adId, MultipartFile photo) throws IOException {
        String name = UUID.randomUUID().toString() + photo.getOriginalFilename();
        File convertFile = new File("src/main/resources/photos/" + name);
        FileOutputStream fileStream = new FileOutputStream(convertFile);
        fileStream.write(photo.getBytes());
        fileStream.close();
        PhotoName photoName = PhotoName.builder().name(name).build();
        Ad ad = adRepository.findById(adId).orElseThrow(IllegalAccessError::new);
        ad.getPhotoNames().add(photoRepository.save(photoName));
        adRepository.save(ad);
        return true;
    }

    @Override
    public boolean likeAd(Long userId, Integer adId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        Ad ad = adRepository.findById(adId).orElseThrow(IllegalAccessError::new);
        Set<Ad> ads = user.getLikedAds();
        if (ads.contains(ad)) {
            ads.remove(ad);
            user.setLikedAds(ads);
            userRepository.save(user);
            return true;
        }
        ads.add(ad);
        user.setLikedAds(ads);
        userRepository.save(user);
        return true;
    }

    @Override
    public boolean deleteAd(Integer id, Long userId) {
        Ad ad = adRepository.findById(id).orElseThrow(IllegalAccessError::new);
        if (ad.getUser().getId().equals(userId)) {
            adRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public AdDto getAdById(Integer id) {
        Ad ad = adRepository.findById(id).orElseThrow(IllegalAccessError::new);
        List<String> photoNames = new LinkedList<>();
        for (PhotoName photoName: ad.getPhotoNames()) {
            photoNames.add(photoName.getName());
        }
        ad.setViews(ad.getViews() + 1);
        User user = ad.getUser();
        return AdDto.from(adRepository.save(ad), user.getUsername(), user.getChatId(), photoNames);
    }

    @Override
    public List<AdFeedDto> getFavorites(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        return user.getLikedAds().stream().sorted(Ad::compareTo).map(x -> AdFeedDto.from(x, true)).collect(Collectors.toList());
    }

    @Override
    public List<AdDto> getUserAds(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(IllegalAccessError::new);
        return user.getAds().stream().sorted().map(AdDto::from).collect(Collectors.toList());
    }
}
