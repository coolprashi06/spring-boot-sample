package com.prashast.service;

import com.prashast.dto.User;
import com.prashast.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserModelService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User dbUser = userRepository.findByUsername(username);
        if(dbUser != null){
            List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
            List<String> dbUserRoles = dbUser.getRoles();
            for(String role : dbUserRoles){
                authorities.add(new SimpleGrantedAuthority(role));
            }
            org.springframework.security.core.userdetails.User user = new org.springframework.security.core.userdetails.User(dbUser.getUsername(),dbUser.getPassword(),authorities);
            Authentication authentication = new UsernamePasswordAuthenticationToken(user,null,authorities);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return user;
        }
        return null;
    }

}
