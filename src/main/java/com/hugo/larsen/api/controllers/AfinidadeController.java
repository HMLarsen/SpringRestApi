package com.hugo.larsen.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.larsen.api.domain.dto.AfinidadeRequest;
import com.hugo.larsen.api.services.AfinidadeService;

/**
 * Endpoint para serviços da afinidade.
 * 
 * @author hugo
 */
@RestController
@RequestMapping(path = "auth/afinidade")
public class AfinidadeController {

	@Autowired
	AfinidadeService afinidadeService;

	/**
	 * Cria uma afinidade.
	 * 
	 * @param request informações da afinidade
	 * @return código {@link HttpStatus#CREATED}
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid AfinidadeRequest request) {
		afinidadeService.save(request);
		return ResponseEntity.created(null).build();
	}

}
