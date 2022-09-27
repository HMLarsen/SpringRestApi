package com.hugo.larsen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo.larsen.api.domain.dto.ScoreRequest;
import com.hugo.larsen.api.domain.model.Score;
import com.hugo.larsen.api.repository.ScoreRepository;

/**
 * Serviços para score.
 * 
 * @see ScoreRequest
 * @see ScoreRepository
 * @see Score
 * @author hugo
 */
@Service
public class ScoreService {

	@Autowired
	ScoreRepository scoreRepository;

	/**
	 * Salva um score no banco de dados.
	 * 
	 * @param request informações do score
	 * @return o score salvo no banco de dados
	 */
	public Score save(ScoreRequest request) {
		Score score = new Score();
		score.setDescricao(request.scoreDescricao());
		score.setInicialScore(request.inicialScore());
		score.setFinalScore(request.finalScore());
		return scoreRepository.save(score);
	}

}
