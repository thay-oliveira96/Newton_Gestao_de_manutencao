package com.tvSoftware.newton.domain.dtos;

import java.io.Serializable;

import com.tvSoftware.newton.domain.Maquina;

public class MaquinaDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	
	private String nome;
	private Integer departamento;
	private String observacoes;
	private String nomeDepartamento;
	
	public MaquinaDTO() {
		super();
	}
	
	public MaquinaDTO(Maquina obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.departamento = obj.getDepartamento().getId();
		this.observacoes = obj.getObservacoes();
		this.nomeDepartamento = obj.getDepartamento().getNome();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Integer departamento) {
		this.departamento = departamento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getNomeDepartamento() {
		return nomeDepartamento;
	}

	public void setNomeDepartamento(String nomeDepartamento) {
		this.nomeDepartamento = nomeDepartamento;
	}

}
