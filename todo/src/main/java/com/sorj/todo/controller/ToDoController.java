package com.sorj.todo.controller;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorj.todo.config.JWTLoginFilter;
import com.sorj.todo.model.ToDo;
import com.sorj.todo.model.User;

@RestController
@RequestMapping("/todo/api/v1")
public class ToDoController {

	private final Logger log = LoggerFactory.getLogger(JWTLoginFilter.class);

	@GetMapping(path = "/todos")
	public ResponseEntity<List<ToDo>> listAllToDosOfUser() {

		String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		log.info("User Name in Controller: " + userName);

		User user = new User();
		user.setFirstName("Steve");

		ToDo todo = new ToDo();
		todo.setCreatedBy(user);
		todo.setTodo("Test ToDo Text");
		todo.setId(new Long(1));

		List<ToDo> todos = new ArrayList<>();
		todos.add(todo);

		return new ResponseEntity<List<ToDo>>(todos, HttpStatus.OK);
	}

}
