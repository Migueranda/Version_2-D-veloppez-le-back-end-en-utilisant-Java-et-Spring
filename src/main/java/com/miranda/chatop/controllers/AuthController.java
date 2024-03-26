package com.miranda.chatop.controllers;

import com.miranda.chatop.configuration.UserAuthProvider;
import com.miranda.chatop.model.dtos.SignUpDto;
import com.miranda.chatop.model.dtos.CredentialsDto;
import com.miranda.chatop.model.dtos.UserDto;
import com.miranda.chatop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AuthController {

    private final UserService userService;
    private final UserAuthProvider userAuthProvider;

    @Operation(description = "Cette méthode permet la connecxion d'un utilisateur")
    //Cette méthode permet la connecxion d'un utilisateur
    // renvoie les detaille de l'utlisateur et un token
    @PostMapping("auth/login")
    public ResponseEntity<UserDto> login(@RequestBody CredentialsDto credentialsDto) {
        UserDto user = userService.login(credentialsDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.ok(user);
    }
    @Operation(description = "cette méthode recupère des informations sur l'utilisateur connecté" )
    //cette méthode recupère des informations sur l'utilisateur connecté
    @GetMapping("auth/me")
    public ResponseEntity<UserDto> getCurrentUser(){
        UserDto currentUser = userService.getCurrentUser();
        return ResponseEntity.ok(currentUser);
    }

    @Operation(description = "cette methode gère l'enregistrement d'un nouvel utilisateur")
    //cette methode gère l'enregistrement d'un nouvel utilisateur
    // puis renvoie les detailles de cet nouvel utilisateur crée ainsi qu'un token
    @PostMapping("auth/register")
    public ResponseEntity<UserDto> register(@RequestBody SignUpDto signUpDto) {
        UserDto user = userService.register(signUpDto);
        user.setToken(userAuthProvider.createToken(user));
        return ResponseEntity.created(URI.create("/user/" + user.getId())).body(user);
    }
}
