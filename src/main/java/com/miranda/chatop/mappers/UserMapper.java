package com.miranda.chatop.mappers;

import com.miranda.chatop.model.dtos.SignUpDto;
import com.miranda.chatop.model.dtos.UserDto;
import com.miranda.chatop.model.entities.UserEntity;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(UserEntity userEntity);

  //@Mapping(target = "password", ignore = true)
    UserEntity signUpToUser(SignUpDto signUpDto);


}

