package com.itis.nsgames.demo.service.adService;

import com.itis.nsgames.demo.dto.ad.AdCreateForm;
import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.dto.ad.AdFeedDto;
import com.itis.nsgames.demo.dto.ad.AdIdForm;
import com.itis.nsgames.demo.model.Ad;
import com.itis.nsgames.demo.model.Game;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface AdService {
    boolean likeAd(Long userId, Integer adId);
    boolean deleteAd(Integer id, Long userId);
    boolean saveAdPhoto(Integer adId, MultipartFile photo) throws IOException;

    AdDto getAdById(Integer id);
    AdIdForm saveAd(Long userId, AdCreateForm ad);

    List<AdFeedDto> getFeed(Long userId);
    List<Game> getTradeList(Integer id);
    List<AdDto> getUserAds(Long userId);
    List<AdFeedDto> searchBy(String name, Long userId);
    List<AdFeedDto> getFavorites(Long userId);
}
