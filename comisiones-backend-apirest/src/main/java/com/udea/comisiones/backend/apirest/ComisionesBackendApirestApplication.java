package com.udea.comisiones.backend.apirest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com.udea.comisiones.backend.apirest"})
public class ComisionesBackendApirestApplication {

	public static void main(String[] args) {
		SpringApplication.run(ComisionesBackendApirestApplication.class, args);
	}

}
