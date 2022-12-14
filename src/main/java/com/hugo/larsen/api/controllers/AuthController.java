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

/**
 * Endpoint para serviços de autenticação.
 * 
 * @author hugo
 */
@RestController
@RequestMapping(path = "public")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtUtils jwtTokenUtil;

	/**
	 * Realiza a autenticação de um usuário.
	 * 
	 * @param request informações da autenticação
	 * @return código {@link HttpStatus#NO_CONTENT} contendo o JWT no header da resposta
	 */
	@PostMapping("login")
	public ResponseEntity<Void> login(@RequestBody @Valid AuthRequest request) {
		try {
			UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(request.usuario(), request.senha());
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