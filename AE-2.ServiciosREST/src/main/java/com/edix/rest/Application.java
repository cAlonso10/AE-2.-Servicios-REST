package com.edix.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Application {

	public static void main(String[] args) {
		System.out.println("Cargando el servicio Rest...");
		SpringApplication.run(Application.class, args);
		System.out.println("Servicio Rest cargado con exito");
	}

}
