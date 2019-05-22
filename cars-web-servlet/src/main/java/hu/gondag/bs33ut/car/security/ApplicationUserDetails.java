package hu.gondag.bs33ut.car.security;

import java.util.Collections;
import java.util.List;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import hu.gondag.bs33ut.car.dto.UserDTO;

public class ApplicationUserDetails implements UserDetails {
    private static final long serialVersionUID = 1L;

    private final UserDTO user;

    public ApplicationUserDetails(UserDTO user) {
        this.user = user;
    }

    @Override
    public List<SimpleGrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority("USER"));
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        // Ez a legrosszabb megoldás de jó van ez ide.
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // Ez a legrosszabb megoldás de jó van ez ide.
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // Ez a legrosszabb megoldás de jó van ez ide.
        return true;
    }

    @Override
    public boolean isEnabled() {
        // Ez a legrosszabb megoldás de jó van ez ide.
        return true;
    }

    public UserDTO getUser() {
        return user;
    }

}
