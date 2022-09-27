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

/**
 * Serviços para pessoa.
 * 
 * @see PessoaRequest
 * @see PessoaRepository
 * @see Pessoa
 * @author hugo
 */
@Service
public class PessoaService {

	@Autowired
	PessoaRepository pessoaRepository;

	@Autowired
	AfinidadeRepository afinidadeRepository;

	@Autowired
	ScoreRepository scoreRepository;

	/**
	 * Salva uma pessoa no banco de dados.
	 * 
	 * @param request informações da pessoa
	 * @return a pessoa salva no banco de dados
	 */
	public Pessoa save(PessoaRequest request) {
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(request.nome());
		pessoa.setTelefone(request.telefone());
		pessoa.setIdade(request.idade());
		pessoa.setCidade(request.cidade());
		pessoa.setEstado(request.estado());
		pessoa.setScore(request.score());
		pessoa.setRegiao(request.regiao());
		pessoa.setDataInclusao(new Date());
		return pessoaRepository.save(pessoa);
	}

	/**
	 * @param id id da pessoa
	 * @return a pessoa no banco de dados relacionada com o id repassado
	 */
	public Optional<Pessoa> getById(long id) {
		return pessoaRepository.findById(id);
	}

	/**
	 * @return todas as pessoas no banco de dados
	 */
	public List<Pessoa> getAll() {
		return pessoaRepository.findAll();
	}

	/**
	 * Converte uma pessoa do banco de dados para uma pessoa a ser visualizada pelo cliente.
	 * 
	 * @param pessoa pessoa do banco de dados
	 * @return a pessoa que o cliente irá ver
	 */
	public PessoaView toView(Pessoa pessoa) {
		Afinidade afinidade = afinidadeRepository.findByRegiao(pessoa.getRegiao());
		List<EstadosEnum> estados = afinidade != null ? afinidade.getEstados() : null;
		Score score = scoreRepository.findScoreBetween(pessoa.getScore());
		String scoreDescricao = score != null ? score.getDescricao() : null;
		return new PessoaView(pessoa.getNome(), pessoa.getTelefone(), pessoa.getIdade(), scoreDescricao, estados);
	}

	/**
	 * Converte pessoas usando o {@link #toView(Pessoa)} para cada pessoa da lista.
	 * 
	 * @param pessoas lista de pessoas no banco de dados
	 * @return lista de pessoas a ser visualizada pelo cliente
	 */
	public List<PessoaView> toViewAll(List<Pessoa> pessoas) {
		return pessoas.stream().map(this::toView).collect(Collectors.toList());
	}

}
