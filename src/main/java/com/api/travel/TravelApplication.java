package com.api.travel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TravelApplication implements CommandLineRunner {

	private final BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	public TravelApplication(BCryptPasswordEncoder bCryptPasswordEncoder) {
		this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(TravelApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		String password = bCryptPasswordEncoder.encode("yarelosa");
		System.out.println("Password generado: " + password);
	}
}
