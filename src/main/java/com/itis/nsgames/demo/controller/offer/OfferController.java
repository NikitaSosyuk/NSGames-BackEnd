package com.itis.nsgames.demo.controller.offer;

import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.dto.game.GameDto;
import com.itis.nsgames.demo.dto.offer.OfferDto;
import com.itis.nsgames.demo.model.Offer;
import com.itis.nsgames.demo.repository.GameRepository;
import com.itis.nsgames.demo.security.token.ApplicationUserDetails;
import com.itis.nsgames.demo.service.offerService.OfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OfferController {
    private final OfferService offerService;

    public OfferController(OfferService offerService) {
        this.offerService = offerService;
    }

    @GetMapping(value = "ad/offers/{id}")
    public ResponseEntity<List<OfferDto>> getOfferList(@PathVariable(value="id") Integer id) {
        return ResponseEntity.ok(offerService.getOffersById(id));
    }

    @GetMapping(value = "ad/getTradeGames")
    public ResponseEntity<List<GameDto>> getOfferTradeGameList(@RequestBody OfferDto offer) {
        return ResponseEntity.ok(offerService.getGameList(offer.getId()));
    }

    @PostMapping(value = "/ad/createOffer")
    public ResponseEntity<?> createOffer(@AuthenticationPrincipal ApplicationUserDetails userDetails, @RequestBody OfferDto offerDto) {
        if (offerService.createOffer(offerDto, userDetails.getUser().getId())) {
            return ResponseEntity.ok(null);
        }
        return ResponseEntity.status(440).body(null);
    }
}
