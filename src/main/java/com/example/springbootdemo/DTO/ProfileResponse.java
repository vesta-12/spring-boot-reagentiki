package com.example.springbootdemo.DTO;

import com.example.springbootdemo.model.Role;
import com.example.springbootdemo.model.UserStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProfileResponse {
    private Long id;
    private String username;
    private String email;
    private Role role;
    private UserStatus status;
}
