package com.hugo.larsen.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.larsen.api.domain.dto.ScoreRequest;
import com.hugo.larsen.api.services.ScoreService;

/**
 * Endpoint para serviços do score.
 * 
 * @author hugo
 */
@RestController
@RequestMapping(path = "auth/score")
public class ScoreController {

	@Autowired
	ScoreService scoreService;

	/**
	 * Cria um score.
	 * 
	 * @param request informações do score
	 * @return código {@link HttpStatus#CREATED}
	 */
	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid ScoreRequest request) {
		scoreService.save(request);
		return ResponseEntity.created(null).build();
	}

}
