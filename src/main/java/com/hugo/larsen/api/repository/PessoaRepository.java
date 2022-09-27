package com.hugo.larsen.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo.larsen.api.domain.model.Pessoa;

/**
 * Repositório para {@link Pessoa}.
 * 
 * @author hugo
 */
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
}
