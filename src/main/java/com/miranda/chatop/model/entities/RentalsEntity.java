package com.miranda.chatop.model.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.miranda.chatop.model.dtos.RentalsDto;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import jakarta.persistence.*;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "rentals")

public class RentalsEntity { // réprensente une entité dans ma base de données.
    public  RentalsEntity (RentalsDto rentalsDto){
        Date date = new Date();
        this.setId(rentalsDto.getId());
        this.setName(rentalsDto.getName());
        this.setSurface(rentalsDto.getSurface());
        this.setPrice(rentalsDto.getPrice());
        this.setPicture(rentalsDto.getPicture());
        this.setDescription(rentalsDto.getDescription());
        this.setOwner_id(rentalsDto.getOwner_id());
        this.setCreated_at(new Timestamp(date.getTime()));
        this.setUpdated_at(new Timestamp(date.getTime()));
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "SURFACE")
    private Integer surface;

    @Column(name = "PRICE")
    private Integer price;

    @Column(name = "PICTURE")
    private String picture;

    @Column(name = "DESCRIPTION")
    private String description;

    @Column(name = "OWNER_ID")
    private Integer owner_id;

    @Column(name = "CREATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Timestamp created_at;

    @Column(name = "UPDATED_AT")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy/MM/dd")
    private Timestamp updated_at;

}
