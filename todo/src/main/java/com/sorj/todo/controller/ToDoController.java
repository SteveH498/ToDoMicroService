package com.sorj.todo.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sorj.todo.config.JWTLoginFilter;

@RestController
public class ToDoController {
	
	private final Logger log = LoggerFactory.getLogger(JWTLoginFilter.class);
	
	@GetMapping(path="/")
	public void root(){
	}

}
