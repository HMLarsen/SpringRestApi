package com.hugo.larsen.api.domain.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Representa uma requisição de score.
 * 
 * @author hugo
 */
public record ScoreRequest(
	@NotBlank String scoreDescricao,
	@NotNull @JsonProperty("inicial") int inicialScore,
	@NotNull @JsonProperty("final") int finalScore
) { }