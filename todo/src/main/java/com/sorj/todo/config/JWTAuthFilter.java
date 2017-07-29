package com.sorj.todo.config;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import com.sorj.todo.util.JWTTokenService;

public class JWTAuthFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(JWTAuthFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Authorization: Bearer
		// eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJTdGV2ZSJ9.Er6MxeZtN_I051Cq1WG3VLztGyE12f6rVRUcgMdfvVQ
		String authHeader = request.getHeader("Authorization");

		if (authHeader == null || !authHeader.toLowerCase().startsWith("bearer")) {
			throw new SecurityException();
		}

		String token = authHeader.split(" ")[1];

		log.info("Found Token: " + token);

		try {

			JWTTokenService tokenService = new JWTTokenService();
			String userName = tokenService.getTokenSubject(token);

			log.info("User Name: " + userName);

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));
			Authentication auth = new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
			
			// Once the request has been authenticated, the Authentication will usually be stored in a thread-local
			// SecurityContext managed by the SecurityContextHolder by the authentication mechanism which is being used.
			SecurityContextHolder.getContext().setAuthentication(auth);

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			throw new SecurityException();

		}

	}

}
