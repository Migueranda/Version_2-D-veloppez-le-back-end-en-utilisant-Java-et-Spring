package com.miranda.chatop.model.dtos;

import com.miranda.chatop.model.entities.UserEntity;
import lombok.*;

import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {

    private Integer id;
    private String name;
    private String email;
    private String password;
    private String token;
    private Timestamp created_at;
    private Timestamp updated_at;
    public static UserDto convertToDto (UserEntity userEntity){
        UserDto userDto = new UserDto();
        userDto.setId(userEntity.getId());
        userDto.setName(userEntity.getName());
        userDto.setEmail(userEntity.getEmail());
        userDto.setPassword(userEntity.getPassword());
        userDto.setToken(userEntity.getToken());
        userDto.setCreated_at(userEntity.getCreated_at());
        userDto.setUpdated_at(userEntity.getUpdated_at());

        return userDto;
    }

}

