package com.hugo.larsen.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo.larsen.api.domain.model.Afinidade;

/**
 * Repositório para {@link Afinidade}.
 * 
 * @author hugo
 */
public interface AfinidadeRepository extends JpaRepository<Afinidade, Long> {

	Optional<Afinidade> findByRegiao(String regiao);

}
