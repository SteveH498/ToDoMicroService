package com.sorj.todo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sorj.todo.model.ToDoUser;

public interface UserCrudRepository extends CrudRepository<ToDoUser, Long> {
	
	
	public ToDoUser findByUserName(String name);

}
