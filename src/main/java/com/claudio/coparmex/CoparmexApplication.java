package com.claudio.coparmex;

import com.claudio.coparmex.models.entities.NotPartner;
import com.claudio.coparmex.models.entities.Person;
import com.claudio.coparmex.services.contracts.NotPartnerDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CoparmexApplication {



	public static void main(String[] args) {
		SpringApplication.run(CoparmexApplication.class, args);
	}



}
