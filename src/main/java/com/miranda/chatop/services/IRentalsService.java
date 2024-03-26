package com.miranda.chatop.services;

import com.miranda.chatop.model.dtos.RentalsDto;

import java.io.IOException;
import java.util.List;

public interface IRentalsService {// l'inversion de contrôle (IoC) :permet de changer facilement l'implémentation du service sans changer le code client.
    List<RentalsDto> getRentals();
    RentalsDto getRentalsEntity(final Long id);

    RentalsDto upDateRentals(RentalsDto rentalsDto);

    RentalsDto saveRentalsEntity(RentalsDto rentalsDto) throws IOException;

    byte[] downloadImageFromFileSystem(String name) throws IOException;

}
