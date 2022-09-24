package com.hugo.larsen.api.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.hugo.larsen.api.domain.model.EstadosEnum;

public record PessoaRequest(
	@NotBlank String nome,
	@NotBlank String telefone,
	@NotNull short idade,
	@NotBlank String cidade,
	@NotNull EstadosEnum estado,
	@NotNull short score,
	@NotBlank String regiao
) { }