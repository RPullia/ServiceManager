package com.robp.serviceManager;

import com.robp.serviceManager.domain.entity.ApplicationEntity;
import com.robp.serviceManager.enumeration.Status;
import com.robp.serviceManager.repository.ApplicationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServiceManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServiceManagerApplication.class, args);
	}
/*
	@Bean
	CommandLineRunner run(ApplicationRepository applicationRepository){
		return args -> {
			applicationRepository.save(new ApplicationEntity(null, "192.168.1.27", 8088, "Test Service", "Test", "http://localhost:8090/application/image/leaf.png", Status.SERVICE_DOWN));
		};
	}*/
}
