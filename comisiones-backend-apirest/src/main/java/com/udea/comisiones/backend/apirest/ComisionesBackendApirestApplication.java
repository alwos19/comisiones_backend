package com.udea.comisiones.backend.apirest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
//@EntityScan(basePackages = {"com.udea.comisiones.backend.apirest"})
public class ComisionesBackendApirestApplication implements CommandLineRunner{

	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	public static void main(String[] args) {
		SpringApplication.run(ComisionesBackendApirestApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = "1234";
		for (int i = 0; i < 5; i++) {
			String passwordBcrypt = passwordEncoder.encode(password);
			System.out.println(passwordBcrypt);
		}
	}

}
