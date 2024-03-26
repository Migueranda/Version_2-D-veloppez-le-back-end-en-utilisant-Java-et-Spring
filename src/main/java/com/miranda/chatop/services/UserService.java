package com.miranda.chatop.services;


import com.miranda.chatop.exception.AppException;
import com.miranda.chatop.mappers.UserMapper;
import com.miranda.chatop.model.dtos.CredentialsDto;
import com.miranda.chatop.model.dtos.SignUpDto;
import com.miranda.chatop.model.dtos.UserDto;
import com.miranda.chatop.model.entities.UserEntity;
import com.miranda.chatop.repositories.UserRepository;

import jakarta.persistence.EntityNotFoundException;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.nio.CharBuffer;
import java.sql.Timestamp;
import java.util.Optional;

@Data
@Service
@RequiredArgsConstructor
public class UserService implements IUserService{

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;


    //Cette méthode permet de récuppèrer  tous les utilisateur de la base de données
    public Iterable<UserEntity> getUser(){
        return userRepository.findAll();
    }

    //ette méthode récuppère l'entité utilisateur en fonction de son id puis la convertit en dto
    @Override
    public UserDto getUserEntity(final Integer id){
        UserEntity userEntity = userRepository.findById(id).orElse(null);
        if (userEntity == null) {
            throw new EntityNotFoundException("UserEntity not found for id: " + id);
        }
        return UserDto.convertToDto(userEntity);
    }

    //cette methode enregistre un nouvel utilisateur dans la base de donneées
    @Override
    public UserDto register(SignUpDto signUpDto) {
        Optional<UserEntity> oUser = userRepository.findByEmail(signUpDto.email());

        if (oUser.isPresent()){
            throw new AppException("User already exists", HttpStatus.BAD_REQUEST);
        }
        UserEntity user = userMapper.signUpToUser(signUpDto);
        user.setPassword(passwordEncoder.encode(CharBuffer.wrap(signUpDto.password())));

        user.setCreated_at(new Timestamp(System.currentTimeMillis()));
        user.setUpdated_at(user.getCreated_at());
        UserEntity savedUser = userRepository.save(user);
        return userMapper.toUserDto(savedUser);
    }

    //cette méthode vérifie les informations de connexion fournies par l'utilisateur
    @Override
    public UserDto login(CredentialsDto credentialsDto) {
       UserEntity user = userRepository.findByEmail(credentialsDto.email())
               .orElseThrow(() -> new AppException("Unkown user", HttpStatus.NOT_FOUND));
       if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.password()),
               user.getPassword())) {
           return userMapper.toUserDto(user);
       }
        throw new AppException("Invalide password",HttpStatus.BAD_REQUEST);
    }

    //cette méthode récuppère les détails de l'utilisateur connecté
    public UserDto getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || !authentication.isAuthenticated()){
            return null;
        }
        UserDto userDto = (UserDto) authentication.getPrincipal();

        return userDto;
    }
}
