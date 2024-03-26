package com.miranda.chatop.model.dtos;

import com.miranda.chatop.model.entities.MessageEntity;

import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class MessageDto {
    private Long id;
    private Integer rental_id;
    private Integer user_id;
    private String message;
    private Timestamp created_at;
    private Timestamp updated_at;

    public static  MessageDto convertToDto(MessageEntity messageEntity){

        MessageDto  messageDto = new MessageDto();
        messageDto.setId(messageEntity.getId());
        messageDto.setRental_id(messageDto.getRental_id());
        messageDto.setCreated_at(messageDto.getCreated_at());
        messageDto.setUpdated_at(messageDto.getUpdated_at());

        return messageDto;
    }


}


