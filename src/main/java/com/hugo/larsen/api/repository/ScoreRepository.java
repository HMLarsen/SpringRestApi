package com.hugo.larsen.api.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.hugo.larsen.api.domain.model.Score;

/**
 * Repositório para {@link Score}.
 * 
 * @author hugo
 */
public interface ScoreRepository extends JpaRepository<Score, Long> {

	@Query(value = "SELECT * FROM score WHERE ?1 BETWEEN inicial AND final", nativeQuery = true)
	Optional<Score> findScoreBetween(short score);

}
