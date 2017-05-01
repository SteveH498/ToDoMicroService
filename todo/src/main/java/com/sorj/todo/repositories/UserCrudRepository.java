package com.sorj.todo.repositories;

import org.springframework.data.repository.CrudRepository;

import com.sorj.todo.model.User;

public interface UserCrudRepository extends CrudRepository<User, Long> {
	
	
	public User findByUserName(String name);

}
