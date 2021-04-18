package com.itis.nsgames.demo.controller.ad;

import com.itis.nsgames.demo.dto.ad.*;
import com.itis.nsgames.demo.dto.user.UserIdForm;
import com.itis.nsgames.demo.service.adService.AdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdController {
    @Autowired
    private AdService adService;

    @PostMapping("/ad/like")
    public ResponseEntity<?> likeAd(@RequestBody AdLikeForm adLikeForm) {
        if (adService.likeAd(adLikeForm.getUserId(), adLikeForm.getId())) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(404).body(null);
    }

    @GetMapping("/ad/detail")
    public ResponseEntity<AdDto> getDetailAd(@RequestBody AdIdForm adIdForm) {
        return ResponseEntity.ok(adService.getAdById(adIdForm.getId()));
    }

    @GetMapping("/ad/feed")
    public ResponseEntity<List<AdFeedDto>> getFeed(@RequestBody UserIdForm userIdForm) {
        return ResponseEntity.ok(adService.getFeed(userIdForm.getId()));
    }

    @GetMapping("/ad/favorites")
    public ResponseEntity<List<AdFeedDto>> getFavorites(@RequestBody UserIdForm userIdForm) {
        return ResponseEntity.ok(adService.getFavorites(userIdForm.getId()));
    }

    @PostMapping("/ad/create")
    public ResponseEntity<AdDto> createAd(@RequestBody AdCreateForm adCreateForm) {
        return ResponseEntity.ok(adService.saveAd(adCreateForm));
    }
}
