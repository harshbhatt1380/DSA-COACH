package com.example.dsacoach.Security;

import java.util.Collection;


import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class UserDetail implements UserDetails 
{
    private final String username;
    private final String password;
    private final Collection<? extends GrantedAuthority> authorities;

    public UserDetail(String username,String password, Collection<? extends GrantedAuthority> authorities)
    {
        this.username=username;
        this.password=password;
        this.authorities=authorities;
    }
    public String getUsername()
    {
        return username;
    }  
    
    public String getPassword()
    {
        return password;
    }
    
    public Collection<? extends GrantedAuthority> getAuthorities()
    {
        return authorities;
    } 
}
