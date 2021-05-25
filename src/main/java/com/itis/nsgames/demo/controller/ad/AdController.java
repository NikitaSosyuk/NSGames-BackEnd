package com.itis.nsgames.demo.controller.ad;

import com.itis.nsgames.demo.dto.ad.*;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.security.token.ApplicationUserDetails;
import com.itis.nsgames.demo.service.adService.AdService;
import org.springframework.http.*;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;

@RestController
public class AdController {
    private final AdService adService;

    public AdController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping("/ad/feed")
    public ResponseEntity<List<AdFeedDto>> getFeed(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(adService.getFeed(userDetails.getUser().getId()));
    }

    @GetMapping("/ad/search/{name}")
    public ResponseEntity<List<AdFeedDto>> getFeed(@AuthenticationPrincipal ApplicationUserDetails userDetails, @PathVariable(value="name") String name) {
        return ResponseEntity.ok(adService.searchBy(name, userDetails.getUser().getId()));
    }

    @GetMapping("/ad/favorites")
    public ResponseEntity<List<AdFeedDto>> getFavorites(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(adService.getFavorites(userDetails.getUser().getId()));
    }

    @GetMapping("ad/tradeList/{id}")
    public ResponseEntity<List<Game>> getGames(@PathVariable(value="id") Integer id) {
        return ResponseEntity.ok(adService.getTradeList(id));
    }

    @PostMapping(value = "/ad/create")
    public ResponseEntity<AdIdForm> createAd(@AuthenticationPrincipal ApplicationUserDetails userDetails, @RequestBody AdCreateForm form) {
        return ResponseEntity.ok(adService.saveAd(userDetails.getUser().getId(), form));
    }

    @PostMapping(value = "/ad/uploadPhoto/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> uploadPhoto(@PathVariable(value="id") Integer id, @RequestParam(name = "photo") MultipartFile photo) throws IOException {
        if (adService.saveAdPhoto(id, photo)) {
            return ResponseEntity.ok().body(null);
        }
        return ResponseEntity.status(403).body(null);
    }

    @PostMapping("/ad/like")
    public ResponseEntity<?> likeAd(@AuthenticationPrincipal ApplicationUserDetails userDetails, @RequestBody AdIdForm adIdForm) {
        if (adService.likeAd(userDetails.getUser().getId(), adIdForm.getId())) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(404).body(null);
    }

    @DeleteMapping("/ad/delete/{id}")
    public ResponseEntity<?> deleteAd(@AuthenticationPrincipal ApplicationUserDetails userDetails, @PathVariable(value="id") Integer id) {
        if (adService.deleteAd(id, userDetails.getUser().getId())) {
            return ResponseEntity.ok(true);
        }
        return ResponseEntity.status(440).body(false);
    }

    @PostMapping("/ad/detail")
    public ResponseEntity<AdDto> getDetailAd(@RequestBody AdIdForm adIdForm) {
        return ResponseEntity.ok(adService.getAdById(adIdForm.getId()));
    }
}
