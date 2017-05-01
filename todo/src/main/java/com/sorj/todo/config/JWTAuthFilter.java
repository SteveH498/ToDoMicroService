package com.sorj.todo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.GenericFilterBean;

public class JWTAuthFilter extends GenericFilterBean {

	private final Logger log = LoggerFactory.getLogger(JWTAuthFilter.class);

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain filterChain)
			throws IOException, ServletException {

		log.info("Check Token");

		filterChain.doFilter(req, res);
	}

}
