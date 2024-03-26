package com.miranda.chatop.services;

import com.miranda.chatop.model.dtos.CredentialsDto;
import com.miranda.chatop.model.dtos.RentalsDto;
import com.miranda.chatop.model.dtos.SignUpDto;
import com.miranda.chatop.model.dtos.UserDto;

public interface IUserService {
    UserDto getUserEntity(final Integer id);

    UserDto login(CredentialsDto credentialsDto);

    UserDto register(SignUpDto signUpDto);
}
