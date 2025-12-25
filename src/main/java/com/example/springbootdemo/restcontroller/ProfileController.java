package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.ProfileResponse;
import com.example.springbootdemo.DTO.UpdateProfileRequest;
import com.example.springbootdemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final AuthService authService;

    @GetMapping("/me")
    public ResponseEntity<ProfileResponse> me(Authentication auth) {
        ProfileResponse profile = authService.getProfile(auth.getName());
        return profile != null ? ResponseEntity.ok(profile) : ResponseEntity.notFound().build();
    }

    @PutMapping("/me")
    public ResponseEntity<ProfileResponse> update(Authentication auth, @RequestBody UpdateProfileRequest req) {
        ProfileResponse updated = authService.updateProfile(auth.getName(), req);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.badRequest().build();
    }
}
