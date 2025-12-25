package com.example.springbootdemo.DTO;

import com.example.springbootdemo.model.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminUserCreateRequest {
    private String username;
    private String email;
    private String password;
    private Role role;
}
