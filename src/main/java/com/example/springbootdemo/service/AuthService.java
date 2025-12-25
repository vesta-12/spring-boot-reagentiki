package com.example.springbootdemo.service;

import com.example.springbootdemo.DTO.*;
import com.example.springbootdemo.jwt.JwtService;
import com.example.springbootdemo.model.AppUser;
import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.UserStatus;
import com.example.springbootdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public ProfileResponse register(RegisterRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) return null;
        if (userRepository.existsByEmail(req.getEmail())) return null;

        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(Role.USER);
        user.setStatus(UserStatus.ACTIVE);

        AppUser saved = userRepository.save(user);

        return new ProfileResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getRole(),
                saved.getStatus()
        );
    }

    public AuthResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        String token = jwtService.generateToken(req.getUsername());
        return new AuthResponse(token);
    }

    public boolean changePassword(String username, ChangePasswordRequest req) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return false;

        if (!passwordEncoder.matches(req.getOldPassword(), user.getPasswordHash())) {
            return false;
        }

        user.setPasswordHash(passwordEncoder.encode(req.getNewPassword()));
        userRepository.save(user);
        return true;
    }

    public ProfileResponse getProfile(String username) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;

        return new ProfileResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getRole(),
                user.getStatus()
        );
    }

    public ProfileResponse updateProfile(String username, UpdateProfileRequest req) {
        AppUser user = userRepository.findByUsername(username).orElse(null);
        if (user == null) return null;

        if (req.getEmail() != null && !req.getEmail().isBlank()) {
            if (!user.getEmail().equals(req.getEmail()) && userRepository.existsByEmail(req.getEmail())) {
                return null;
            }
            user.setEmail(req.getEmail());
        }

        AppUser saved = userRepository.save(user);

        return new ProfileResponse(
                saved.getId(),
                saved.getUsername(),
                saved.getEmail(),
                saved.getRole(),
                saved.getStatus()
        );
    }
}
