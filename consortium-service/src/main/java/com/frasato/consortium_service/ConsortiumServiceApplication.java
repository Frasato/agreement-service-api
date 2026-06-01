package com.frasato.consortium_service;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class ConsortiumServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsortiumServiceApplication.class, args);
	}

}
