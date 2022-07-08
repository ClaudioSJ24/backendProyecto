package com.claudio.coparmex.models.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class UserM implements UserDetails {
    private String name;
    private String userM;
    private String lastname;
    private String phone;
    private String email;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public UserM(String name, String userM, String lastname, String phone, String email, String password, Collection<? extends GrantedAuthority> authorities) {
        this.name = name;
        this.userM = userM;
        this.lastname = lastname;
        this.phone = phone;
        this.email = email;
        this.password = password;
        this.authorities = authorities;
    }

    public static UserM build(Partner user){

        List<GrantedAuthority> authorities =
                user.getRoles().stream().map(rol -> new SimpleGrantedAuthority(
                        rol.getNameRol().name())).collect(Collectors.toList());
        return new UserM(user.getName(), user.getUser(),user.getLastname(), user.getPhone(), user.getEmail(), user.getPassword(), authorities);


    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userM;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }
}
