package com.tvSoftware.newton.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.tvSoftware.newton.domain.Departamentos;

public class DepartamentosDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message = "O campo PRIORIDADE é requerido")
	private String nome;
	
	public DepartamentosDTO() {
		super();	
	}
	
	public DepartamentosDTO(Departamentos obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
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

}
