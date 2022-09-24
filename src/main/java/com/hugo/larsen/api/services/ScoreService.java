package com.hugo.larsen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo.larsen.api.domain.dto.ScoreRequest;
import com.hugo.larsen.api.domain.model.Score;
import com.hugo.larsen.api.repository.ScoreRepository;

@Service
public class ScoreService {

	@Autowired
	ScoreRepository scoreRepository;

	public void save(ScoreRequest request) {
		Score score = new Score();
		score.setDescricao(request.scoreDescricao());
		score.setInicialScore(request.inicialScore());
		score.setFinalScore(request.finalScore());
		scoreRepository.save(score);
	}

}
