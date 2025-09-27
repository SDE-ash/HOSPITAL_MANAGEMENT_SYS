package com.hms.user.UsreMS.service;
import org.springframework.stereotype.Service;

import com.hms.user.UsreMS.dto.UserDTO;

@Service
public interface UserServices {

    public void registerUser(UserDTO userDTO);

    public UserDTO loginUser(UserDTO userDTO);

    public UserDTO getUserById(Long id);

    public UserDTO updateUser(Long id, UserDTO userDTO);

}
