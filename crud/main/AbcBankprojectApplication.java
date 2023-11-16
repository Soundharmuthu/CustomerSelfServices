package com.crud.main;

import org.springframework.boot.SpringApplication;



import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.primesoftinc.message.service.CoreService;
import com.primesoftinc.message.service.CoreServiceImpl;

@SpringBootApplication
@ComponentScan("com.crud")
@EntityScan("com.crud.model")
@EnableJpaRepositories("com.crud.repository")
public class AbcBankprojectApplication {

	public static void main(String[] args) {
		SpringApplication.run(AbcBankprojectApplication.class, args);
	}
	
	@Bean
	public CoreService coreService() {
		return new CoreServiceImpl();
	}

}
