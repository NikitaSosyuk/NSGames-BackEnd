package com.itis.nsgames.demo.controller.user;

import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.security.token.ApplicationUserDetails;
import com.itis.nsgames.demo.service.adService.AdService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    private final AdService adService;

    public UserController(AdService adService) {
        this.adService = adService;
    }

    @GetMapping(value = "user/ads")
    public ResponseEntity<List<AdDto>> getAdList(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(adService.getUserAds(userDetails.getUser().getId()));
    }
}
