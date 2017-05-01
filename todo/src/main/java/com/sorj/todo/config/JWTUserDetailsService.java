package com.sorj.todo.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.sorj.todo.model.User;
import com.sorj.todo.repositories.UserCrudRepository;

@Service
public class JWTUserDetailsService implements UserDetailsService {

	@Autowired
	UserCrudRepository userRepositoy;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

		User user = userRepositoy.findByUserName(username);
		if (user == null || user.getUserName() == null) {
			throw new UsernameNotFoundException("Invalid user");
		}

		List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
		grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

		UserDetails userDetails = new org.springframework.security.core.userdetails.User(user.getUserName(),
				user.getPassword(), grantedAuthorities);

		return userDetails;
	}

}
