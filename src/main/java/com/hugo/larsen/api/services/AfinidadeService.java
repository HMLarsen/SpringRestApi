package com.hugo.larsen.api.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo.larsen.api.domain.dto.AfinidadeRequest;
import com.hugo.larsen.api.domain.model.Afinidade;
import com.hugo.larsen.api.repository.AfinidadeRepository;

@Service
public class AfinidadeService {

	@Autowired
	AfinidadeRepository afinidadeRepository;

	public Afinidade save(AfinidadeRequest request) {
		Afinidade afinidade = new Afinidade();
		afinidade.setRegiao(request.regiao());
		afinidade.setEstados(request.estados());
		return afinidadeRepository.save(afinidade);
	}

}
