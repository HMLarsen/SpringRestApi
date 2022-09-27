package com.hugo.larsen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo.larsen.api.domain.dto.AfinidadeRequest;
import com.hugo.larsen.api.domain.model.Afinidade;
import com.hugo.larsen.api.repository.AfinidadeRepository;

/**
 * Serviços para afinidade.
 * 
 * @see AfinidadeRequest
 * @see AfinidadeRepository
 * @see Afinidade
 * @author hugo
 */
@Service
public class AfinidadeService {

	@Autowired
	AfinidadeRepository afinidadeRepository;

	/**
	 * Salva uma afinidade no banco de dados.
	 * 
	 * @param request informações da afinidade
	 * @return a afinidade salva no banco de dados
	 */
	public Afinidade save(AfinidadeRequest request) {
		Afinidade afinidade = new Afinidade();
		afinidade.setRegiao(request.regiao());
		afinidade.setEstados(request.estados());
		return afinidadeRepository.save(afinidade);
	}

}
