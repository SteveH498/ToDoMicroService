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

import io.jsonwebtoken.Jwts;

public class JWTAuthFilter extends OncePerRequestFilter {

	private final Logger log = LoggerFactory.getLogger(JWTAuthFilter.class);

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String token = request.getHeader("Authentication");

		log.info("Found Token: " + token);

		try {

			String userName = Jwts.parser().setSigningKey("secret").parseClaimsJws(token).getBody().getSubject();

			log.info("User Name: " + userName);

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			grantedAuthorities.add(new SimpleGrantedAuthority("USER"));

			Authentication auth = new UsernamePasswordAuthenticationToken(userName, null, grantedAuthorities);
			SecurityContextHolder.getContext().setAuthentication(auth);

			filterChain.doFilter(request, response);

		} catch (Exception e) {
			throw new SecurityException();

		}

	}

}
