package com.miranda.chatop.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miranda.chatop.model.dtos.UserDto;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.sql.Timestamp;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Data
@Table(name = "users")
public class UserEntity  {

    public UserEntity(UserDto userDto){
        Date date = new Date();

        this.setId(userDto.getId());
        this.setName(userDto.getName());
        this.setEmail(userDto.getEmail());
        this.setPassword(userDto.getPassword());
        this.setToken(userDto.getToken());
        this.setCreated_at(new Timestamp(date.getTime()));
        this.setUpdated_at(new Timestamp(date.getTime()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "EMAIL")
    private String email;

    @Column(name = "PASSWORD")
    private String password;

    @Transient
    private String token;

    @Column(name = "CREATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Timestamp created_at;

    @Column(name = "UPDATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Timestamp updated_at;

}


