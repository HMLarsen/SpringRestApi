package com.hugo.larsen.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hugo.larsen.api.domain.model.Afinidade;

/**
 * Repositório para {@link Afinidade}.
 * 
 * @author hugo
 */
public interface AfinidadeRepository extends JpaRepository<Afinidade, Long> {

	Afinidade findByRegiao(String regiao);

}
