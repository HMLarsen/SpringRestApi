package com.hugo.larsen.api.domain.dto;

import javax.validation.constraints.NotBlank;

public record AuthRequest(
	@NotBlank String username,
	@NotBlank String password
) { }