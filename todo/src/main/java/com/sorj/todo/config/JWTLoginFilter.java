package com.sorj.todo.config;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sorj.todo.model.UserLogin;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTLoginFilter extends AbstractAuthenticationProcessingFilter {

	private final Logger log = LoggerFactory.getLogger(JWTLoginFilter.class);

	protected JWTLoginFilter(String httpMethod, String defaultFilterProcessesUrl,
			AuthenticationManager authenticationManager) {
		// This filter will intercept a request and attempt to perform
		// authentication from that request if the request matches.
		super(new AntPathRequestMatcher(defaultFilterProcessesUrl, httpMethod));
		// The filter requires that you set the authenticationManager property.
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		// Get user login data from request body
		UserLogin userLogin = new ObjectMapper().readValue(request.getInputStream(), UserLogin.class);

		// Authenticate via AuthenticationManager
		Authentication auth = new UsernamePasswordAuthenticationToken(userLogin.getUsername(), userLogin.getPassword(),
				null);
		return getAuthenticationManager().authenticate(auth);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {

		String compactJWT = Jwts.builder().setSubject(authResult.getName()).signWith(SignatureAlgorithm.HS256, "secret")
				.compact();

		response.setContentType("application/json");

		JSONObject jsonObject = new JSONObject();

		try {
			jsonObject.put("Token", compactJWT);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		response.getOutputStream().print(jsonObject.toString());
	}

}
