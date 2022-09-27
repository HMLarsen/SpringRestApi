package com.hugo.larsen.api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;

import com.hugo.larsen.api.domain.dto.AfinidadeRequest;
import com.hugo.larsen.api.domain.model.Afinidade;
import com.hugo.larsen.api.domain.model.EstadosEnum;
import com.hugo.larsen.api.repository.AfinidadeRepository;
import com.hugo.larsen.api.services.AfinidadeService;

/**
 * Teste para afinidade.
 * 
 * @see AfinidadeRepository
 * @see AfinidadeService
 * @see Afinidade
 * @author hugoo
 */
@RunWith(MockitoJUnitRunner.class)
@ExtendWith(MockitoExtension.class)
public class AfinidadeTest {

	@Mock
	private AfinidadeRepository mockAfinidadeRepository;

	@InjectMocks
	private AfinidadeService afinidadeService;

	@Before
	public void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	/**
	 * Testa se a criação de uma afinidade pelo serviço é executada corretamente.
	 */
	@Test
	public void shouldCreateAfinidade() {
		String regiao = "sudeste";
		List<EstadosEnum> estados = Lists.list(EstadosEnum.SP, EstadosEnum.RJ, EstadosEnum.MG, EstadosEnum.ES);

		when(mockAfinidadeRepository.save(any(Afinidade.class))).thenAnswer(i -> i.getArguments()[0]);
		AfinidadeRequest request = new AfinidadeRequest(regiao, estados);
		Afinidade result = afinidadeService.save(request);

		// comparação
		assertEquals(regiao, result.getRegiao());
		assertEquals(estados, result.getEstados());
	}

}
