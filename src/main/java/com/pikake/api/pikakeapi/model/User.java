package com.pikake.api.pikakeapi.model;

import com.pikake.api.pikakeapi.entiy.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String username;
    private String password;
    private String email;
    private Role role;
    private boolean enabled;
}