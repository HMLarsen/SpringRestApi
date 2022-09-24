package com.hugo.larsen.api.security.jwt;

import java.util.Base64;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtUtils {

	private String issuer = "Hugo Marcel Larsen";
	private String secret = "kcj8FWaW%-yb6amm@#$@#%G2547h654234";
	private long expirationMs = TimeUnit.MINUTES.toMillis(30);

	public String generateAccessToken(UserDetails userdetails) {
		long currentTimeMillis = System.currentTimeMillis();
		return Jwts.builder()
			.setIssuer(issuer)
			.setSubject(userdetails.getUsername())
			.setIssuedAt(new Date(currentTimeMillis))
			.setExpiration(new Date(currentTimeMillis + expirationMs))
			.signWith(SignatureAlgorithm.HS256, Base64.getEncoder().encodeToString(secret.getBytes())).compact();
	}

	public boolean validate(String token) {
		try {
			Jwts.parserBuilder()
				.setSigningKey(secret.getBytes())
				.build()
				.parseClaimsJws(token);
			return true;
		} catch (Exception e) {
		}
		return false;
	}

	public String getUsername(String token) {
		return Jwts.parserBuilder()
			.setSigningKey(secret.getBytes())
			.build()
			.parseClaimsJws(token)
			.getBody()
			.getSubject();
	}

}
