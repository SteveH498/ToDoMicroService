package com.sorj.todo.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorj.todo.config.JWTLoginFilter;
import com.sorj.todo.model.ToDo;
import com.sorj.todo.model.ToDoUser;
import com.sorj.todo.repositories.ToDoCrudRepository;
import com.sorj.todo.repositories.UserCrudRepository;

@RestController
@RequestMapping("/todo/api/v1")
@CrossOrigin
public class ToDoController {

	private final Logger log = LoggerFactory.getLogger(JWTLoginFilter.class);

	@Autowired
	UserCrudRepository userCrudRepository;

	@Autowired
	ToDoCrudRepository toDoCrudRepository;

	@GetMapping(path = "/todos")
	public ResponseEntity<List<ToDo>> listAllToDosOfUser() {

		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("User Name in Controller: " + userName);

		List<ToDo> todos = toDoCrudRepository.findByCreatedByUserName(userName);

		return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);
	}

	@PostMapping(path = "/user")
	public ResponseEntity<ToDoUser> createUser(@RequestBody ToDoUser newUser) {
		log.info("Create new User");
		log.info("User name: " + newUser.getUserName());
		log.info("User password: " + newUser.getPassword());

		ToDoUser createdUser = userCrudRepository.save(newUser);

		return new ResponseEntity<ToDoUser>(createdUser, HttpStatus.CREATED);
	}

	@PostMapping(path = "/todo")
	public ResponseEntity<ToDo> addToDo(@RequestBody ToDo todo) {

		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		ToDoUser createdByUser = userCrudRepository.findByUserName(userName);

		todo.setCreatedBy(createdByUser);

		ToDo savedToDo = toDoCrudRepository.save(todo);

		return new ResponseEntity<ToDo>(savedToDo, HttpStatus.CREATED);
	}

}
