package com.hugo.larsen.api.domain.dto;

import javax.validation.constraints.NotBlank;

/**
 * Representa uma requisição de autenticação.
 * 
 * @author hugo
 */
public record AuthRequest(
	@NotBlank String usuario,
	@NotBlank String senha
) { }