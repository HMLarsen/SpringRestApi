package com.hugo.larsen.api.domain.dto;

import java.util.List;

import com.hugo.larsen.api.domain.model.EstadosEnum;

public record PessoaView(
	String nome,
	String telefone,
	short idade,
	String scoreDescricao,
	List<EstadosEnum> estados
) { }