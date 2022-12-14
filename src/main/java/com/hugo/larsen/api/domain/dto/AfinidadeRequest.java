package com.hugo.larsen.api.domain.dto;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.hugo.larsen.api.domain.model.EstadosEnum;

/**
 * Representa uma requisição de afinidade.
 * 
 * @author hugo
 */
public record AfinidadeRequest(
	@NotBlank String regiao,
	@NotEmpty List<EstadosEnum> estados
) { }