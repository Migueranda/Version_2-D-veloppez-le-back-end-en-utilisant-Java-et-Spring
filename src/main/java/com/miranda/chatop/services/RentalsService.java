package com.miranda.chatop.services;

import com.miranda.chatop.model.dtos.RentalsDto;
import com.miranda.chatop.model.entities.RentalsEntity;
import com.miranda.chatop.repositories.RentalRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Data
@Service
public class RentalsService implements IRentalsService {
    //le service RentalsService encapsule les opérations CRUD de base pour les entités RentalsEntity, en utilisant les méthodes fournies par le RentalRepository
    // Le service utilise le RentalRepository pour interagir avec la base de données
    private final RentalRepository rentalRepository;
    @Value("${file.upload.dir}")
    private String Folder_PATH;


    @Autowired
    public RentalsService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }


    //Cette méthode récupère les detail d'une location en fonction de son id
    @Override
    public RentalsDto getRentalsEntity(final Long id) {
        RentalsEntity rentalsEntity = rentalRepository.findById(id).orElse(null);
        if (rentalsEntity == null) {
            throw new EntityNotFoundException("RentalsEntity not found for id: " + id);
        }
        return RentalsDto.convertToDto(rentalsEntity);
    }

    //Cette méthode récupère toutes les locations disponibles dans la base de données
    @Override
    public List<RentalsDto> getRentals() {//Récupération de la liste de toutes les locations disponibles.
        Iterable<RentalsEntity> rentalsEntities = rentalRepository.findAll();
        //utilisation de StreamSupport pour convertir l'itérable de locations en une liste de DTO
        // pour transformer les entités en objets DTO avant de les renvoyer à l'utilisateur.
        List<RentalsDto> result = StreamSupport.stream(rentalsEntities.spliterator(), false)
                .map(RentalsDto::convertToDto)
                .collect(Collectors.toList());
        return result;
    }

    @Override
    public RentalsDto saveRentalsEntity(RentalsDto rentalsDto) throws IOException {

        RentalsEntity rentalsEntity = rentalRepository.save(new RentalsEntity(rentalsDto));

        // Vérifier si l'entité a été sauvegardée avec succès
        if (rentalsEntity != null) {
            // Convertir et renvoyer le DTO
            return RentalsDto.convertToDto(rentalsEntity);
        } else {
            // Erreur de sauvegarde RentalsEntity
            throw new IOException("Failed to save entity");
        }
    }

    //cette méthode gère la sauvegarde d'une nouvelle location dans la base de données
  @Override
  public RentalsDto upDateRentals(RentalsDto rentalsDto){
      Date date = new Date();
      // Vérifier si l'entité de location existe
      RentalsEntity rentalsEntity = rentalRepository.findById(rentalsDto.getId())
              .orElseThrow(() -> new EntityNotFoundException("RentalsEntity not found for id: " + rentalsDto.getId()));

      // Mettre à jour les champs de l'entité avec les valeurs du DTO
      rentalsEntity.setName(rentalsDto.getName());
      rentalsEntity.setSurface(rentalsDto.getSurface());
      rentalsEntity.setPrice(rentalsDto.getPrice());
      rentalsEntity.setDescription(rentalsDto.getDescription());
      rentalsEntity.setUpdated_at(new Timestamp(date.getTime()));

      // Sauvegarder l'entité mise à jour dans la base de données
      RentalsEntity updatedEntity = rentalRepository.save(rentalsEntity);

      // Convertir et retourner le DTO de l'entité mise à jour
      return RentalsDto.convertToDto(updatedEntity);
  }

  //Cette methode est utilisé pour récupérer les données binaire d'une image dans le repertoire
  @Override
  public byte[] downloadImageFromFileSystem(String picture) throws IOException {

        String filePath = Folder_PATH + File.separator + picture;
        byte[] image = Files.readAllBytes(new File(filePath).toPath());
        return image;
    }
  }
































