package com.sorj.todo.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.sorj.todo.model.ToDo;
import com.sorj.todo.model.User;

public interface ToDoCrudRepository extends CrudRepository<ToDo, Long> {
	
	List<ToDo> findByCreatedBy(User user);
	
	List<ToDo> findByCreatedByUserName(String userName);
	

}
