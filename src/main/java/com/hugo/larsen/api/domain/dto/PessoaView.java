package com.hugo.larsen.api.domain.dto;

import java.util.List;

import com.hugo.larsen.api.domain.model.EstadosEnum;

/**
 * Representa uma pessoa a ser visualizada pelo cliente.
 * 
 * @author hugo
 */
public record PessoaView(
	String nome,
	String telefone,
	short idade,
	String scoreDescricao,
	List<EstadosEnum> estados
) { }