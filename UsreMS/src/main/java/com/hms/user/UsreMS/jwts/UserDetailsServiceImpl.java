package com.hms.user.UsreMS.jwts;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.hms.user.UsreMS.Exceptions.HmsExceptions;
import com.hms.user.UsreMS.repo.UserRepo;

public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepo.findByEmail(email)
        .map(user -> new CustomUsersDetails(user))
        .orElseThrow(() -> new HmsExceptions("user not found with email "+ email));


    }

}
