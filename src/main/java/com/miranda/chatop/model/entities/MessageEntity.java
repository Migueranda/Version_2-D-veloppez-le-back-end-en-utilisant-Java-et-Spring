package com.miranda.chatop.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miranda.chatop.model.dtos.MessageDto;

import jakarta.persistence.*;

import lombok.Data;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Table(name = "messages")
public class MessageEntity {
  public  MessageEntity (MessageDto messageDto){
      Date date = new Date();

      this.setId(messageDto.getId());
      this.setRental_id(messageDto.getRental_id());
      this.setUser_id(messageDto.getUser_id());
      this.setMessage(messageDto.getMessage());
      this.setCreated_at(new Timestamp(date.getTime()));
      this.setUpdated_at(new Timestamp(date.getTime()));
    }

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "RENTAL_ID")
    private Integer rental_id;

    @Column(name = "USER_ID")
    private Integer user_id;

    @Column(name = "MESSAGE")
    private String message  ;

    @Column(name = "CREATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Timestamp created_at;

    @Column(name = "UPDATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Timestamp updated_at;
}
