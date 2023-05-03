package com.danielhh.proyectoDAM;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * The type Proyecto dam application.
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Microservcio de Gestion de Tareas",
								description = "Este microservicio se ha realizado como proyecto final",
								version = "1.0"))
public class ProyectoDamApplication {

	/**
	 * The entry point of application.
	 *
	 * @param args the input arguments
	 */
	public static void main(String[] args) {
		SpringApplication.run(ProyectoDamApplication.class, args);
	}
}
