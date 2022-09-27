package com.hugo.larsen.api;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hugo.larsen.api.domain.model.EstadosEnum;
import com.hugo.larsen.api.domain.model.Pessoa;
import com.hugo.larsen.api.repository.AfinidadeRepository;
import com.hugo.larsen.api.repository.PessoaRepository;
import com.hugo.larsen.api.repository.ScoreRepository;
import com.hugo.larsen.api.services.PessoaService;

/**
 * Teste para pessoa.
 * 
 * @see PessoaRepository
 * @see PessoaService
 * @see Pessoa
 * @author hugoo
 */
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class PessoaTest {

	@Mock
	private PessoaRepository mockPessoaRepository;

	@Mock
	private AfinidadeRepository mockAfinidadeRepository;

	@Mock
	private ScoreRepository mockScoreRepository;

	@InjectMocks
	private PessoaService pessoaService;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	public Pessoa getPessoa() {
		// informações da pessoa esperada
		Long id = 1l;
		String nome = "Hugo M.";
		String telefone = "4799999999";
		short idade = (short) 25;
//		String scoreDescricao = "Recomendável";
//		List<EstadosEnum> estados = Lists.list(EstadosEnum.SP, EstadosEnum.RJ, EstadosEnum.MG, EstadosEnum.ES);

		// informações de transição
		short score = (short) 750;
//		short scoreInicial = 701;
//		short scoreFinal = 1000;
		String cidade = "São Paulo";
		EstadosEnum estado = EstadosEnum.SP;
		String regiao = "sudeste";

		// pessoa
		Pessoa pessoa = new Pessoa();
		pessoa.setId(id);
		pessoa.setNome(nome);
		pessoa.setTelefone(telefone);
		pessoa.setIdade(idade);
		pessoa.setScore(score);
		pessoa.setCidade(cidade);
		pessoa.setEstado(estado);
		pessoa.setRegiao(regiao);
		return pessoa;
	}

	@Test
	public void shouldCreatePessoa() {
//		Pessoa pessoa = getPessoa();
//		
//		when(mockPessoaRepository.save(any(Pessoa.class))).thenReturn(pessoa);
//
//		// afinidade
//		Afinidade afinidade = new Afinidade();
//		afinidade.setRegiao(regiao);
//		afinidade.setEstados(estados);
//		when(mockAfinidadeRepository.findByRegiao(regiao)).thenReturn(afinidade);
//
//		// score
//		Score recomendavelScore = new Score();
//		recomendavelScore.setDescricao(scoreDescricao);
//		recomendavelScore.setInicialScore(scoreInicial);
//		recomendavelScore.setFinalScore(scoreFinal);
//		when(mockScoreRepository.findScoreBetween(score)).thenReturn(recomendavelScore);
//
//		// resultado
//		PessoaRequest pessoaRequest = new PessoaRequest(nome, telefone, idade, cidade, estado, score, regiao);
//		Pessoa pessoaSaved = pessoaService.save(pessoaRequest);
//		PessoaView pessoaView = pessoaService.toView(pessoaSaved);
//		assertEquals(nome, pessoaView.nome());
//		assertEquals(telefone, pessoaView.telefone());
//		assertEquals(idade, pessoaView.idade());
//		assertEquals(scoreDescricao, pessoaView.scoreDescricao());
//		assertEquals(estados, pessoaView.estados());
	}

}
