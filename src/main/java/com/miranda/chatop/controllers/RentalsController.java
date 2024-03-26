package com.miranda.chatop.controllers;

import com.miranda.chatop.model.dtos.RentalsDto;
import com.miranda.chatop.model.dtos.UserDto;
import com.miranda.chatop.services.IRentalsService;
import com.miranda.chatop.services.UserService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

@RestController
@SecurityRequirement(name = "chatop")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class RentalsController {// exposition des endpoints pour gérer les opérations CRUD
    @Autowired
    private UserService userService;
    @Autowired
    private IRentalsService rentalsService;
    @Value("${file.upload.dir}")
    private String uploadDir;
    @Operation(description = "Récupération de la liste de toutes les location disponibles")
    @GetMapping("/rentals")//Récupération de la liste de toutes les location disponibles.
    public Map<String, List<RentalsDto>> getRentals(){
        return Map.of("rentals", rentalsService.getRentals());
    }

    @Operation(description = "Récuperation d'une location spécifique en fonction de son identifiant")
    @GetMapping("/rentals/{id}")//Récuperation d'une location spécifique en fonction de son identifiant
    public  RentalsDto getRentalsById(@PathVariable Long id){
        return rentalsService.getRentalsEntity(id);
    }

    @Operation(description = "Cette méthode permet de céer de nouvelles locations")
    //Cette méthode permet de céer de nouvelles locations
    @PostMapping(value = "/rentals", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public RentalsDto postRentalsEntity(@RequestParam("picture") MultipartFile file, @RequestParam("name") String name, @RequestParam("surface") Integer surface, @RequestParam("price") Integer price, @RequestParam("description") String description) throws IOException {

        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File destFile = new File(uploadDir + File.separator + fileName );
        file.transferTo(destFile);

        // récupération de l'Id de l'utilisateur authentifié pour set le owner_id
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserDto userDto = (UserDto) authentication.getPrincipal();

        RentalsDto rentalsDto = new RentalsDto();
        rentalsDto.setName(name);
        rentalsDto.setSurface(surface);
        rentalsDto.setPrice(price);
        rentalsDto.setDescription(description);
        rentalsDto.setPicture(fileName);
        rentalsDto.setOwner_id(userDto.getId());

        return rentalsService.saveRentalsEntity(rentalsDto);
    }

    @Operation(description = "Cette méthode permet de mettre à jours une location existante")
    //Cette méthode permet de mettre à jours une location existante
    @PutMapping("/rentals/{id}")// Mise à jour une location existante
    public RentalsDto putRentalsById(@RequestParam("name") String name, @RequestParam("surface") Integer surface, @RequestParam("price") Integer price, @RequestParam("description") String description, @PathVariable Long id) throws IOException {
        RentalsDto rentalsDto = new RentalsDto();
        rentalsDto.setId(id);
        rentalsDto.setName(name);
        rentalsDto.setSurface(surface);
        rentalsDto.setPrice(price);
        rentalsDto.setDescription(description);

        return  rentalsService.upDateRentals(rentalsDto);
    }
    @Operation(description = "cette méthode permet de télécharger une image")
    //cette méthode permet de télécharger une image
    @GetMapping("/fileSystem/{picture}")
    public ResponseEntity<?> downloadImageFromFileSystem(@PathVariable String picture) throws IOException{

        byte[]  imageData = rentalsService.downloadImageFromFileSystem(picture);
        return ResponseEntity.status(HttpStatus.OK)
        .body(imageData);
    }
}
