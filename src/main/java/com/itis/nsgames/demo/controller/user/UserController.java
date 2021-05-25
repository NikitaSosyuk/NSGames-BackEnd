package com.itis.nsgames.demo.controller.user;

import com.itis.nsgames.demo.dto.ad.AdDto;
import com.itis.nsgames.demo.dto.user.UserDto;
import com.itis.nsgames.demo.security.token.ApplicationUserDetails;
import com.itis.nsgames.demo.service.adService.AdService;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class UserController {
    private final AdService adService;
    private final UserService userService;

    public UserController(AdService adService, UserService userService) {
        this.adService = adService;
        this.userService = userService;
    }

    @GetMapping(value = "user/ads")
    public ResponseEntity<List<AdDto>> getAdList(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(adService.getUserAds(userDetails.getUser().getId()));
    }

    @GetMapping(value = "user/info")
    public ResponseEntity<UserDto> getUserInfo(@AuthenticationPrincipal ApplicationUserDetails userDetails) {
        return ResponseEntity.ok(UserDto.from(userService.getById(userDetails.getUser().getId())));
    }

    @PostMapping(value = "user/logout")
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }
}
