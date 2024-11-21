package com.example.skgsss.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.stream.Collectors;

public class UserPrincipal implements UserDetails {

    private final User user;

    public UserPrincipal(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // Convert roles to GrantedAuthority
        return user.getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_"+user.getRoles()))
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true; // Adjust as per your application logic
    }

    @Override
    public boolean isAccountNonLocked() {
        return true; // Adjust as per your application logic
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true; // Adjust as per your application logic
    }

    @Override
    public boolean isEnabled() {
        return user.getEnabled(); // Ensure this field is correctly mapped in your User entity
    }
}
