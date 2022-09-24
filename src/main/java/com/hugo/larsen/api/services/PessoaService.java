package com.hugo.larsen.api.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hugo.larsen.api.domain.dto.PessoaRequest;
import com.hugo.larsen.api.domain.dto.PessoaView;
import com.hugo.larsen.api.domain.model.Afinidade;
import com.hugo.larsen.api.domain.model.EstadosEnum;
import com.hugo.larsen.api.domain.model.Pessoa;
import com.hugo.larsen.api.domain.model.Score;
import com.hugo.larsen.api.repository.AfinidadeRepository;
import com.hugo.larsen.api.repository.PessoaRepository;
import com.hugo.larsen.api.repository.ScoreRepository;

@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	AfinidadeRepository afinidadeRepository;

	@Autowired
	ScoreRepository scoreRepository;

	public void save(PessoaRequest request) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(request.nome());
		pessoa.setTelefone(request.telefone());
		pessoa.setIdade(request.idade());
		pessoa.setCidade(request.cidade());
		pessoa.setEstado(request.estado());
		pessoa.setScore(request.score());
		pessoa.setRegiao(request.regiao());
		pessoa.setDataInclusao(new Date());
		pessoaRepository.save(pessoa);
	}

	public Optional<Pessoa> getById(long id) {
		return pessoaRepository.findById(id);
	}

	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	public List<PessoaView> toViewAll(List<Pessoa> pessoas) {
		return pessoas.stream().map(this::toView).collect(Collectors.toList());
	}

	public PessoaView toView(Pessoa pessoa) {
		Afinidade afinidade = afinidadeRepository.findByRegiao(pessoa.getRegiao());
		List<EstadosEnum> estados = afinidade != null ? afinidade.getEstados() : null;
		Score score = scoreRepository.findScoreBetween(pessoa.getScore());
		String scoreDescricao = score != null ? score.getDescricao() : null;
		return new PessoaView(pessoa.getNome(), pessoa.getTelefone(), pessoa.getIdade(), scoreDescricao, estados);
	}

}
