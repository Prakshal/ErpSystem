package com.brevitaz.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class EmployeeDetails implements UserDetails
{
    private String firstName;
    private String lastName;
    private String emailID;
    private String token;
    private String id;
    private String password;
    private Collection<? extends GrantedAuthority> authorities;

    public EmployeeDetails(String id,String firstName, String lastName, String password, String emailId,String token, List<GrantedAuthority> grantedAuthorities)
    {
        this.id=id;
        this.firstName=firstName;
        this.lastName=lastName;
        this.emailID=emailId;
        this.authorities=grantedAuthorities;
        this.token=token;
        this.password=password;
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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmailID() {
        return emailID;
    }

    public String getToken() {
        return token;
    }

    public String getId() {
        return id;
    }
}