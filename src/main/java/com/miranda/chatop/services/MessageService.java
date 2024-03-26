package com.miranda.chatop.services;

import com.miranda.chatop.model.dtos.MessageDto;
import com.miranda.chatop.model.entities.MessageEntity;
import com.miranda.chatop.repositories.MessageRepository;
import lombok.Data;
import org.springframework.stereotype.Service;

@Data
@Service
public class MessageService implements IMessageService {
    private final MessageRepository messageRepository;

    //Cette méthode sauvegarde un nouveau message dans la base de données
    @Override
    public MessageDto saveMessageEntity(MessageDto messageDto){
        MessageEntity saveMessageEntity = messageRepository.save(new MessageEntity(messageDto));
        return MessageDto.convertToDto(saveMessageEntity);
    }
}
