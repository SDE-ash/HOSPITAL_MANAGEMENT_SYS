package com.hms.user.UsreMS.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.hms.user.UsreMS.Exceptions.HmsExceptions;
import com.hms.user.UsreMS.dto.UserDTO;
import com.hms.user.UsreMS.entities.User;
import com.hms.user.UsreMS.mapper.UserMapper;
import com.hms.user.UsreMS.repo.UserRepo;

@Service
public class UserServiceImpl  implements UserServices{

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private UserMapper userMapper;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void registerUser(UserDTO userDTO) {
       //check if the user already exists or not i f exist throw exception
       Optional<User> optUser = userRepo.findByEmail(userDTO.getEmail());

       if(optUser.isPresent()){
        throw new HmsExceptions("User already exists with this email");
       }

       userDTO.setPassword(passwordEncoder.encode(userDTO.getPassword()));

       userRepo.save(userMapper.toEntity(userDTO));

    }

    @Override
    public UserDTO loginUser(UserDTO userDTO) {

        User u  = userRepo.findByEmail(userDTO.getEmail()).orElseThrow(()-> new HmsExceptions("USER NOT FOUND WITH THIS CREDENTIALS"));

        if(!passwordEncoder.matches(userDTO.getPassword(), u.getPassword())){
            throw new HmsExceptions("Passsword does not matched");
        }

        u.setPassword(null);

        return userMapper.toDTO(u);
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userMapper.toDTO(userRepo.findById(id).orElseThrow(()-> new HmsExceptions("user not found with the ID")));
    }

    @Override
    public UserDTO updateUser(Long id, UserDTO userDTO) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'updateUser'");
    }

}
