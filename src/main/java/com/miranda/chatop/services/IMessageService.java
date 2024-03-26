package com.miranda.chatop.services;

import com.miranda.chatop.model.dtos.MessageDto;

public interface IMessageService {
    MessageDto saveMessageEntity(MessageDto messageDto);
}
