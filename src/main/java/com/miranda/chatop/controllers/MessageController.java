package com.miranda.chatop.controllers;

import com.miranda.chatop.model.dtos.MessageDto;
import com.miranda.chatop.model.dtos.UserDto;
import com.miranda.chatop.services.IMessageService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SecurityRequirement(name = "chatop")
@CrossOrigin(origins = "http://localhost:4200")
public class MessageController {
    @Autowired
    private IMessageService messageService;
    @Operation(description = "cette méthode permet à un utilisateur authentifié de créer un nouveau message")
    //cette méthode permet à un utilisateur authentifié de créer un nouveau message
    @PostMapping("/messages")
    public MessageDto postMessageEntity(@RequestBody MessageDto messageDto) {

        // récupération de l'Id du user authentifié pour set le user_id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();
        // & modification de la valeur dans l'entity messageDTO
        messageDto.setUser_id(userDto.getId());

        return messageService.saveMessageEntity(messageDto);
    }
}
