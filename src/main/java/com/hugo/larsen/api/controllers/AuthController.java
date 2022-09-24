package com.hugo.larsen.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.larsen.api.domain.dto.AuthRequest;
import com.hugo.larsen.api.security.jwt.JwtUtils;

@RestController
@RequestMapping(path = "api/public")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtTokenUtil;

	@PostMapping("login")
	public ResponseEntity<Void> login(@RequestBody @Valid AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			User user = (User) authenticate.getPrincipal();
			return ResponseEntity.noContent()
				.header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
				.build();
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}