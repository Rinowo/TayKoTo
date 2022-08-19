package com.example.cardealer.security.service;

import com.example.cardealer.model.Users;
import com.example.cardealer.repository.RoleRepository;
import com.example.cardealer.repository.UsersRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Users users = usersRepository.findByUsername(username);
        List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();
        if(users == null){
            new UsernameNotFoundException("Login fail");
        }else {
            //Doan nay dang hard code role, phai lay tu db ra
            //GrantedAuthority authority = new SimpleGrantedAuthority("ROLE_ADMIN");
            //grantList.add(authority);

            List<GrantedAuthority> authorities = users.getRoles().stream()
                    .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                    .collect(Collectors.toList());

            UserDetails userDetails = new org.springframework.security.core.userdetails.User(username,
                    users.getPassword(),authorities);
            return userDetails;
        }

        return UserDetailsImpl.build(users);
    }
}
