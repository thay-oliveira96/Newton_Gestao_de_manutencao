package com.tvSoftware.newton.domain.dtos;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import com.tvSoftware.newton.domain.Defeitos;

public class DefeitosDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@NotNull(message = "O campo PRIORIDADE é requerido")
	private String descricao;
	
	public DefeitosDTO() {
		super();	
	}
	
	public DefeitosDTO(Defeitos obj) {
		super();
		this.id = obj.getId();
		this.descricao = obj.getDescricao();
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
