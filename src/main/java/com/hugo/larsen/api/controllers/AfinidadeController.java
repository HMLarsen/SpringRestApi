package com.hugo.larsen.api.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.larsen.api.domain.dto.AfinidadeRequest;
import com.hugo.larsen.api.services.AfinidadeService;

@RestController
@RequestMapping(path = "api/auth/afinidade")
public class AfinidadeController {

	@Autowired
	AfinidadeService afinidadeService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid AfinidadeRequest request) {
		afinidadeService.save(request);
		return ResponseEntity.created(null).build();
	}

}
