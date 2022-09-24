package com.hugo.larsen.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo.larsen.api.domain.model.Pessoa;

public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
