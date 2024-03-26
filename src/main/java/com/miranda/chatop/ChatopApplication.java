package com.miranda.chatop;

import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@SecurityScheme(name = "chatop", scheme = "bearer", type = SecuritySchemeType.HTTP, in = SecuritySchemeIn.HEADER)
@ComponentScan("com.miranda.chatop")
public class ChatopApplication{
	public static void main(String[] args) {
		SpringApplication.run(ChatopApplication.class, args);
	}

}
