package com.hugo.larsen.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hugo.larsen.api.domain.dto.ScoreRequest;
import com.hugo.larsen.api.domain.model.Score;
import com.hugo.larsen.api.repository.ScoreRepository;
import com.hugo.larsen.api.services.ScoreService;

/**
 * Teste para serviço de score.
 * 
 * @see ScoreRepository
 * @see ScoreService
 * @see Score
 * @author hugoo
 */
@ExtendWith(MockitoExtension.class)
public class ScoreServiceTest {

	@Mock
	private ScoreRepository mockScoreRepository;

	@InjectMocks
	private ScoreService scoreService;

	@Test
	public void shouldCreateScore() {
		String descricao = "Insuficiente";
		short inicialScore = (short) 0;
		short finalScore = (short) 200;

		when(mockScoreRepository.save(any(Score.class))).thenAnswer(i -> i.getArguments()[0]);
		ScoreRequest request = new ScoreRequest(descricao, inicialScore, finalScore);
		Score result = scoreService.save(request);

		// comparação
		assertEquals(descricao, result.getDescricao());
		assertEquals(inicialScore, result.getInicialScore());
		assertEquals(finalScore, result.getFinalScore());
	}

}
