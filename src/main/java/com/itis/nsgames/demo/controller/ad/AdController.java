package com.itis.nsgames.demo.controller.ad;

import com.itis.nsgames.demo.dto.ad.*;
import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.dto.user.UserIdForm;
import com.itis.nsgames.demo.model.Game;
import com.itis.nsgames.demo.security.token.ApplicationUserDetails;
import com.itis.nsgames.demo.service.adService.AdService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public ResponseEntity<Set<AdFeedDto>> getFeed(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(adService.getFeed(userDetails.getUser().getId()));
    }

    @GetMapping("/ad/favorites")
    public ResponseEntity<List<AdFeedDto>> getFavorites(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(adService.getFavorites(userDetails.getUser().getId()));
    }

    @GetMapping("ad/getTradeList/{id}")
    public ResponseEntity<List<Game>> getGames(@PathVariable(value="id") Integer id) {
        return ResponseEntity.ok(adService.getTradeList(id));
    }

    @GetMapping(value = "/image/{photoName}")
    public ResponseEntity<byte[]> getImageAsResponseEntity(@PathVariable(value="photoName") String photoName) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        InputStream in = new FileInputStream(new File("src/main/resources/photos/" + photoName));
        byte[] media = in.readAllBytes();
        headers.setCacheControl(CacheControl.noCache().getHeaderValue());

        return new ResponseEntity<>(media, headers, HttpStatus.OK);
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

    @PostMapping("/ad/detail")
    public ResponseEntity<AdDto> getDetailAd(@RequestBody AdIdForm adIdForm) {
        return ResponseEntity.ok(adService.getAdById(adIdForm.getId()));
    }
}
