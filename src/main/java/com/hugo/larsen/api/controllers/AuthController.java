package com.hugo.larsen.api.controllers;

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
import com.hugo.larsen.api.domain.dto.UserView;
import com.hugo.larsen.api.security.jwt.JwtUtils;

import jakarta.validation.Valid;

@RestController
@RequestMapping(path = "api/public")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtTokenUtil;

	@PostMapping("login")
	public ResponseEntity<UserView> login(@RequestBody @Valid AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.username(), request.password());
			Authentication authenticate = authenticationManager.authenticate(authentication);
			User user = (User) authenticate.getPrincipal();
			UserView userView = new UserView(user.getUsername(), user.getUsername());
			return ResponseEntity.ok()
				.header(HttpHeaders.AUTHORIZATION, jwtTokenUtil.generateAccessToken(user))
				.body(userView);
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}

}