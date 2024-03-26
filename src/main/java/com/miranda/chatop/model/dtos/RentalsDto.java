package com.miranda.chatop.model.dtos;

import com.miranda.chatop.model.entities.RentalsEntity;
import lombok.*;

import java.sql.Timestamp;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RentalsDto {
    private Long id;
    private String name;
    private Integer surface;
    private Integer price;
    private String picture;
    private String description;
    private Integer owner_id;
    private Timestamp created_at;
    private Timestamp updated_at;

    public static RentalsDto convertToDto(RentalsEntity rentalsEntity){

        RentalsDto rentalsDto = new RentalsDto();
        rentalsDto.setId(rentalsEntity.getId());
        rentalsDto.setName(rentalsEntity.getName());
        rentalsDto.setSurface(rentalsEntity.getSurface());
        rentalsDto.setPrice(rentalsEntity.getPrice());
        rentalsDto.setPicture("http://localhost:8080/api/fileSystem/" + rentalsEntity.getPicture());
        rentalsDto.setDescription(rentalsEntity.getDescription());
        rentalsDto.setOwner_id(rentalsEntity.getOwner_id());
        rentalsDto.setCreated_at(rentalsEntity.getCreated_at());
        rentalsDto.setUpdated_at(rentalsEntity.getUpdated_at());

        return rentalsDto;
    }

}
