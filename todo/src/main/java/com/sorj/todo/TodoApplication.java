package com.sorj.todo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.sorj.todo.model.ToDo;
import com.sorj.todo.model.User;
import com.sorj.todo.repositories.ToDoCrudRepository;
import com.sorj.todo.repositories.UserCrudRepository;

@SpringBootApplication
public class TodoApplication implements CommandLineRunner {

	@Autowired
	UserCrudRepository userCrudRepository;
	
	@Autowired
	ToDoCrudRepository toDoCrudRepository;

	public static void main(String[] args) {
		SpringApplication.run(TodoApplication.class, args);
	}

	@Override
	public void run(String... arg0) throws Exception {

		User user1 = new User();
		user1.setUserName("Steve");
		user1.setPassword("Password");
		user1.setLastName("Doe");
		user1.setFirstName("John");
		userCrudRepository.save(user1);
		
		
		ToDo todo1 = new ToDo();		
		todo1.setCreatedBy(user1);
		todo1.setTodo("Test todo1");
		toDoCrudRepository.save(todo1);

	}

}
