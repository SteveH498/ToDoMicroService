package com.sorj.todo.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	private final Logger log = LoggerFactory.getLogger(WebSecurityConfig.class);

	@Autowired
	JWTUserDetailsService userDetailsService;

	@Override
	protected void configure(HttpSecurity http) throws Exception {

		/*
		 * (1) Allow POST requests to /login in order to obtain authentication
		 * token (2) All other urls are protected
		 */
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST, "/login").permitAll().anyRequest()
				.authenticated().and()
				.addFilterBefore(new JWTLoginFilter(HttpMethod.POST.toString(), "/login", authenticationManager()),
						UsernamePasswordAuthenticationFilter.class)
				.addFilterBefore(new JWTAuthFilter(), UsernamePasswordAuthenticationFilter.class);

	}

	@Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		log.info("Build authentication manager");
		// Build a custom AuthenticationManager that uses a custom
		// UserDetailsService to check for valid users
		auth.userDetailsService(userDetailsService);
	}

}
