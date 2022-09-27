package com.hugo.larsen.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hugo.larsen.api.domain.dto.PessoaView;
import com.hugo.larsen.api.domain.model.Afinidade;
import com.hugo.larsen.api.domain.model.EstadosEnum;
import com.hugo.larsen.api.domain.model.Pessoa;
import com.hugo.larsen.api.domain.model.Score;
import com.hugo.larsen.api.repository.AfinidadeRepository;
import com.hugo.larsen.api.repository.PessoaRepository;
import com.hugo.larsen.api.repository.ScoreRepository;
import com.hugo.larsen.api.services.PessoaService;

/**
 * Teste para serviço de pessoa.
 * 
 * @see PessoaRepository
 * @see PessoaService
 * @see Pessoa
 * @author hugoo
 */
@ExtendWith(MockitoExtension.class)
public class PessoaServiceTest {

	@Mock
	private PessoaRepository mockPessoaRepository;

	@Mock
	private AfinidadeRepository mockAfinidadeRepository;

	@Mock
	private ScoreRepository mockScoreRepository;

	@InjectMocks
	private PessoaService pessoaService;

	private static Pessoa getPessoaMock(String nome, String regiao, short score) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setScore(score);
		pessoa.setRegiao(regiao);
		return pessoa;
	}

	private static Afinidade getAfinidadeMock(String regiao, List<EstadosEnum> estados) {
		Afinidade afinidade = new Afinidade();
		afinidade.setRegiao(regiao);
		afinidade.setEstados(estados);
		return afinidade;
	}

	private static Score getScoreMock(String descricao, int inicialScore, int finalScore) {
		Score score = new Score();
		score.setDescricao(descricao);
		score.setInicialScore(inicialScore);
		score.setFinalScore(finalScore);
		return score;
	}

	public static Stream<Arguments> provideParameters() {
		Pessoa pessoaHugo = getPessoaMock("Hugo", "sudeste", (short) 550);
		Afinidade afinidadeSudeste = getAfinidadeMock("sudeste", Lists.list(EstadosEnum.SP, EstadosEnum.RJ, EstadosEnum.MG, EstadosEnum.ES));
		Score scoreAceitavel = getScoreMock("Aceitável", 501, 700);

		Pessoa pessoaEduardo = getPessoaMock("Eduardo", "nordeste", (short) 100);
		Afinidade afinidadeNordeste = getAfinidadeMock("nordeste", Lists.list(EstadosEnum.RN, EstadosEnum.MA));
		Score scoreInsuficiente = getScoreMock("Insuficiente", 0, 200);

		return Stream.of(
			Arguments.of(pessoaHugo, afinidadeSudeste, scoreAceitavel),
			Arguments.of(pessoaEduardo, afinidadeNordeste, scoreInsuficiente)
		);
	}

	@Test
	public void shoudGetById() {
		pessoaService.getById(1);
		verify(mockPessoaRepository, times(1)).findById(any(Long.class));
	}

	@ParameterizedTest
	@MethodSource("provideParameters")
	public void shouldCreatePessoaView(Pessoa pessoa, Afinidade afinidade, Score score) {
		when(mockAfinidadeRepository.findByRegiao(pessoa.getRegiao())).thenReturn(Optional.of(afinidade));
		when(mockScoreRepository.findScoreBetween(pessoa.getScore())).thenReturn(Optional.of(score));

		// resultado
		PessoaView pessoaView = pessoaService.toView(pessoa);
		assertEquals(pessoa.getNome(), pessoaView.nome());
		assertEquals(score.getDescricao(), pessoaView.scoreDescricao());
		assertEquals(afinidade.getEstados(), pessoaView.estados());
	}

}
