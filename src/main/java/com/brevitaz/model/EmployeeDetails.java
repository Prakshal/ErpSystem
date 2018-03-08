package com.brevitaz.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class EmployeeDetails implements UserDetails
{
    public EmployeeDetails() {
    }

    private String token;
    private String id;
    private Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetails(String id,String token, List<GrantedAuthority> grantedAuthorities)
    {
        this.id=id;
        this.authorities=grantedAuthorities;
        this.token=token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return null;
    }

    @Override
    public String getUsername() {
        return null;
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

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }
}