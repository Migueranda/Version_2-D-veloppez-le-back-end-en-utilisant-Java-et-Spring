package com.miranda.chatop.model.dtos;
//cette classe record est une classe immuable qui est  utilisée pour représenter des données simples et transférables.
public record SignUpDto(String name, String email, char[] password) {
}
