package com.test.tyro;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.test.tyro.model.User;
import com.test.tyro.repository.UserRepository;

@SpringBootApplication
public class TyroApplication {

	public static void main(String[] args) {
		SpringApplication.run(TyroApplication.class, args);
	}

	@Bean
	CommandLineRunner runner(UserRepository userRepository){
		System.out.println("im doing things");
		return args -> {
			User user = new User();
			user.setName("Adam");

			List<User> userList = userRepository.findAll();
			for (User item : userList) {
				System.out.println(item.getName());
			}
			//User saved = userRepository.findByID(user.getId()).orElseThrow(NoSuchElementException::new);
		};
	}
}
