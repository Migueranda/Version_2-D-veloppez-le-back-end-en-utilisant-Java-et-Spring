package com.miranda.chatop.controllers;

import com.miranda.chatop.model.dtos.UserDto;
import com.miranda.chatop.services.IUserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityRequirement(name = "chatop")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private IUserService userService;

    @Operation(description = "Cette méthode de contrôleur récupère un utilisateur en fonction de son ID")
    //Cette méthode de contrôleur récupère un utilisateur en fonction de son ID
    @GetMapping("/user/{id}")
    public UserDto getUserById(@PathVariable Integer id){
        return userService.getUserEntity(id);
    }
}
