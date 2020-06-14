package com.scp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.env.AbstractEnvironment;

@SpringBootApplication
public class SpringbootconfigpropertiesApplication {

	public static void main(String[] args) {
		//-Dspring.profiles.active=e0
		System.setProperty(AbstractEnvironment.ACTIVE_PROFILES_PROPERTY_NAME, "e0");
		SpringApplication.run(SpringbootconfigpropertiesApplication.class, args);
	}

}
