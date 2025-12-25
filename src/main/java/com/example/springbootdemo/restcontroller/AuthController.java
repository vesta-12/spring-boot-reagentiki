package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.*;
import com.example.springbootdemo.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ProfileResponse> register(@RequestBody RegisterRequest req) {
        ProfileResponse created = authService.register(req);
        return created != null
                ? ResponseEntity.status(201).body(created)
                : ResponseEntity.badRequest().build();
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest req) {
        AuthResponse resp = authService.login(req);
        return ResponseEntity.ok(resp);
    }

    @PostMapping("/change-password")
    public ResponseEntity<Void> changePassword(Authentication auth, @RequestBody ChangePasswordRequest req) {
        boolean ok = authService.changePassword(auth.getName(), req);
        return ok ? ResponseEntity.ok().build() : ResponseEntity.badRequest().build();
    }
}
