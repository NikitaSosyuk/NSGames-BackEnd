package com.itis.nsgames.demo.controller.admin;

import com.itis.nsgames.demo.dto.user.UserIdForm;
import com.itis.nsgames.demo.model.User;
import com.itis.nsgames.demo.service.userService.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private UserService userService;

    @GetMapping("/admin/getAll")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @PutMapping("/admin/ban")
    public ResponseEntity<?> banAccount(@RequestBody UserIdForm userForm) {
        return ResponseEntity.ok(userService.banUserById(userForm.getId()));
    }

    @PutMapping("/admin/unban")
    public ResponseEntity<?> unbanAccount(@RequestBody UserIdForm userForm) {
        return ResponseEntity.ok(userService.unbanUserById(userForm.getId()));
    }
}
