package com.pikake.api.pikakeapi.entiy;
import jakarta.persistence.*;
import lombok.Data;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;


@Entity
@Data
@Table(name="users")
public class UserEntity{

    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)
    private String password;

    @Column(nullable = true)
    private String email;

    @Column(nullable = true)
    private boolean enabled;

    @Column(nullable = true)
    private Role role;

//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//        return List.of();
//    }
//
//    @Transient
//    private UserEntity userEntity;
//
//    @Override
//    public Collection<? extends GrantedAuthority> getAuthorities() {
//
//        Role role = userEntity.getRole();
//        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
//
//        authorities.add(new SimpleGrantedAuthority(role.name()));
//        return authorities;
//    }
//
//
//    @Override
//    public boolean isAccountNonExpired() {
//        return UserDetails.super.isAccountNonExpired();
//    }
//
//    @Override
//    public boolean isAccountNonLocked() {
//        return UserDetails.super.isAccountNonLocked();
//    }
//
//    @Override
//    public boolean isCredentialsNonExpired() {
//        return UserDetails.super.isCredentialsNonExpired();
//    }
//
//    @Override
//    public boolean isEnabled() {
//        return UserDetails.super.isEnabled();
//    }
}

