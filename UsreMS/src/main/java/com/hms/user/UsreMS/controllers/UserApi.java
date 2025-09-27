package com.hms.user.UsreMS.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hms.user.UsreMS.dto.ResponseDTO;
import com.hms.user.UsreMS.dto.UserDTO;
import com.hms.user.UsreMS.service.UserServices;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
@Validated
@CrossOrigin
public class UserApi {

    @Autowired
    private UserServices userServices;


    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid UserDTO userDTO){
        
        userServices.registerUser(userDTO);

        return new ResponseEntity<>( new ResponseDTO("ACCOUNT CREATED SUCCESSFULLY"), HttpStatus.CREATED);
    }


    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserDTO userDTO){

        return new ResponseEntity<>(userServices.loginUser(userDTO), HttpStatus.OK);
    }


    // @Want to achieve delte eaccoutn and update accoutn using email and id

}
