package com.itis.nsgames.demo.service.adService;

import com.itis.nsgames.demo.dto.ad.AdCreateForm;
import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.dto.ad.AdFeedDto;

import java.util.List;

public interface AdService {
    AdDto saveAd(AdCreateForm ad);
    boolean likeAd(Long userId, Integer adId);
    AdDto getAdById(Integer id);
    List<AdFeedDto> getFavorites(Long userId);
    List<AdFeedDto> getFeed(Long userId);
}
