package com.example.dsacoach.Security;

import java.io.IOException;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.example.dsacoach.service.JwtService;
import com.example.dsacoach.service.UserDetailService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthFilter extends OncePerRequestFilter 
{
    private final JwtService jwtService;
    private final UserDetailService userDetailService;
    public JwtAuthFilter(JwtService jwtService,UserDetailService userDetailService)
    {
        this.jwtService=jwtService;
        this.userDetailService=userDetailService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,HttpServletResponse response,FilterChain filterChain) throws IOException,ServletException
    {
        String authHeader = request.getHeader("Authorization");

        if(authHeader!=null && authHeader.startsWith("Bearer "))
        {
            String token = authHeader.substring(7);

            String username = jwtService.extractUsername(token);

            UserDetail userDetails = userDetailService.loadUserByUsername(username);

            if(jwtService.validateToken(token, userDetails))
            {
                Authentication authentication =new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());

                SecurityContextHolder.getContext().setAuthentication(authentication);
            }
        }
        filterChain.doFilter(request, response);
    }
}
