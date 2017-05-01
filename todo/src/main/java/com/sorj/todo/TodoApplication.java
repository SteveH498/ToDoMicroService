package com.sorj.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sorj.todo.model.User;
import com.sorj.todo.repositories.UserCrudRepository;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	UserCrudRepository userCrudRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		User user1 = new User();
		user1.setUserName("Steve");
		user1.setPassword("Password");

		User user2 = new User();
		user2.setUserName("John");
		user2.setPassword("Password");

		userCrudRepository.save(user1);
		userCrudRepository.save(user2);

	}

}
