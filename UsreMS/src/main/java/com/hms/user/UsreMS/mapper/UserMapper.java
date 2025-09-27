package com.hms.user.UsreMS.mapper;

import org.mapstruct.Mapper;

import com.hms.user.UsreMS.dto.UserDTO;
import com.hms.user.UsreMS.entities.User;



@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
