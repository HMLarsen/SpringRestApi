package com.hugo.larsen.api.domain.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Representa uma afinidade.
 * 
 * @author hugo
 */
@Entity
@Table(name = "afinidade")
public class Afinidade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(length = 50)
	private String regiao;

	@ElementCollection(targetClass = EstadosEnum.class)
	@Enumerated(EnumType.STRING)
	List<EstadosEnum> estados;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRegiao() {
		return regiao;
	}

	public void setRegiao(String regiao) {
		this.regiao = regiao;
	}

	public List<EstadosEnum> getEstados() {
		return estados;
	}

	public void setEstados(List<EstadosEnum> estados) {
		this.estados = estados;
	}

}
