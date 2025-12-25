package com.example.springbootdemo.restcontroller;

import com.example.springbootdemo.DTO.AdminUserCreateRequest;
import com.example.springbootdemo.DTO.ProfileResponse;
import com.example.springbootdemo.model.AppUser;
import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.UserStatus;
import com.example.springbootdemo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/admin/users")
@RequiredArgsConstructor
public class AdminController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @GetMapping
    public ResponseEntity<List<ProfileResponse>> getAll() {
        List<ProfileResponse> list = userRepository.findAll().stream()
                .map(u -> new ProfileResponse(u.getId(), u.getUsername(), u.getEmail(), u.getRole(), u.getStatus()))
                .toList();

        return list.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(list);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProfileResponse> getById(@PathVariable Long id) {
        AppUser u = userRepository.findById(id).orElse(null);
        if (u == null) return ResponseEntity.notFound().build();

        return ResponseEntity.ok(new ProfileResponse(u.getId(), u.getUsername(), u.getEmail(), u.getRole(), u.getStatus()));
    }

    @PostMapping
    public ResponseEntity<ProfileResponse> create(@RequestBody AdminUserCreateRequest req) {
        if (userRepository.existsByUsername(req.getUsername())) return ResponseEntity.badRequest().build();
        if (userRepository.existsByEmail(req.getEmail())) return ResponseEntity.badRequest().build();

        AppUser user = new AppUser();
        user.setUsername(req.getUsername());
        user.setEmail(req.getEmail());
        user.setPasswordHash(passwordEncoder.encode(req.getPassword()));
        user.setRole(req.getRole() != null ? req.getRole() : Role.USER);
        user.setStatus(UserStatus.ACTIVE);

        AppUser saved = userRepository.save(user);

        return ResponseEntity.status(201).body(
                new ProfileResponse(saved.getId(), saved.getUsername(), saved.getEmail(), saved.getRole(), saved.getStatus())
        );
    }

    @PutMapping("/{id}/block")
    public ResponseEntity<Void> block(@PathVariable Long id) {
        AppUser user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        user.setStatus(UserStatus.BLOCKED);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/unblock")
    public ResponseEntity<Void> unblock(@PathVariable Long id) {
        AppUser user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        user.setStatus(UserStatus.ACTIVE);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}/role/{role}")
    public ResponseEntity<Void> changeRole(@PathVariable Long id, @PathVariable Role role) {
        AppUser user = userRepository.findById(id).orElse(null);
        if (user == null) return ResponseEntity.notFound().build();

        user.setRole(role);
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
