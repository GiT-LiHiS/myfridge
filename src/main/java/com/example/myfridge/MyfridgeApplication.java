package com.example.myfridge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myfridge.domain.Item;
import com.example.myfridge.domain.ItemRepository;

@SpringBootApplication
public class MyfridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfridgeApplication.class, args);
	}

	@Bean
	
	public CommandLineRunner demo(ItemRepository itemrepo) {
		
		return (args) -> {
			
		//test
			
			
		};
		
		
	}
	
}
