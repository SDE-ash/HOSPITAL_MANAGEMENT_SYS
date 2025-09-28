package com.hms.user.UsreMS.jwts;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.hms.user.UsreMS.entities.User;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class CustomUsersDetails implements UserDetails{

   private User user;

   public CustomUsersDetails(User user){
        this.user = user;
   }

   @Override
   public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(user.getRole().name()));
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
    // TODO Auto-generated method stub
    return true;
   }

   @Override
   public boolean isAccountNonLocked() {
    // TODO Auto-generated method stub
    return true;
   }

   @Override
   public boolean isCredentialsNonExpired() {
    // TODO Auto-generated method stub
    return true;
   }

   @Override
   public boolean isEnabled() {
    // TODO Auto-generated method stub
    return true;
   }


   





}
