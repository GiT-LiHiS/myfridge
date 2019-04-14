package com.example.myfridge;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.example.myfridge.domain.Item;
import com.example.myfridge.domain.ItemRepository;
import com.example.myfridge.domain.User;
import com.example.myfridge.domain.UserRepository;

@SpringBootApplication
public class MyfridgeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyfridgeApplication.class, args);
	}

	@Bean
	
	public CommandLineRunner demo(ItemRepository itemrepo, UserRepository urepo) {
		
		return (args) -> {

		//test
			Item item = new Item();
			item.setName("testi");
			itemrepo.save(item);
			User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");

			urepo.save(user2);

		};
		
		
	}
	
}
