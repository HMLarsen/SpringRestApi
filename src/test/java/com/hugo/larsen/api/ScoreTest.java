package com.hugo.larsen.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hugo.larsen.api.domain.dto.ScoreRequest;
import com.hugo.larsen.api.domain.model.Score;
import com.hugo.larsen.api.repository.ScoreRepository;
import com.hugo.larsen.api.services.ScoreService;

@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class ScoreTest {

	@Mock
	private ScoreRepository mockScoreRepository;

	@InjectMocks
	private ScoreService scoreService;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void shouldCreateScore() {
		String descricao = "Insuficiente";
		short inicialScore = (short) 0;
		short finalScore = (short) 200;

		// entidade
		Score score = new Score();
		score.setDescricao(descricao);
		score.setInicialScore(inicialScore);
		score.setFinalScore(finalScore);
		when(mockScoreRepository.save(any())).thenReturn(score);

		// save
		ScoreRequest request = new ScoreRequest(descricao, inicialScore, finalScore);
		Score result = scoreService.save(request);

		// comparação
		assertEquals(descricao, result.getDescricao());
		assertEquals(inicialScore, result.getInicialScore());
		assertEquals(finalScore, result.getFinalScore());
	}

}
