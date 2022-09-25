package com.hugo.larsen.api.controllers;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hugo.larsen.api.domain.dto.PessoaRequest;
import com.hugo.larsen.api.domain.dto.PessoaView;
import com.hugo.larsen.api.domain.model.Pessoa;
import com.hugo.larsen.api.services.PessoaService;

@RestController
@RequestMapping(path = "auth/pessoa")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;

	@PostMapping
	public ResponseEntity<Void> create(@RequestBody @Valid PessoaRequest request) {
		pessoaService.save(request);
		return ResponseEntity.created(null).build();
	}

	@GetMapping
	public ResponseEntity<Collection<PessoaView>> get() {
		List<Pessoa> pessoas = pessoaService.getAll();
		if (pessoas != null && !pessoas.isEmpty()) {
			List<PessoaView> viewAll = pessoaService.toViewAll(pessoas);
			return ResponseEntity.ok(viewAll);
		}
		return ResponseEntity.noContent().build();
	}

	@GetMapping("{id}")
	public ResponseEntity<PessoaView> getById(@PathVariable long id) {
		Optional<Pessoa> pessoa = pessoaService.getById(id);
		if (pessoa.isPresent()) {
			PessoaView pessoaView = pessoaService.toView(pessoa.get());
			return ResponseEntity.ok(pessoaView);
		}
		return ResponseEntity.noContent().build();
	}

}
