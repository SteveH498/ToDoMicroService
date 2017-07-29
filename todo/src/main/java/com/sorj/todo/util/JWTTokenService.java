package com.sorj.todo.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

public class JWTTokenService {

	public static final String SECRET = "secret";

	public String createToken(String subject) {

		return Jwts.builder().setSubject(subject).signWith(SignatureAlgorithm.HS256, SECRET).compact();

	}

	public String getTokenSubject(String jwtToken) {
		return Jwts.parser().setSigningKey(SECRET).parseClaimsJws(jwtToken).getBody().getSubject();
	}

}
