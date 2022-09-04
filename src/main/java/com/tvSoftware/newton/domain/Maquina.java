package com.tvSoftware.newton.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tvSoftware.newton.domain.dtos.MaquinaDTO;

@Entity
public class Maquina implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	private String departamento;
	private String observacoes;
	
	public Maquina() {
		super();
	}
	
	public Maquina(MaquinaDTO maquinaDTO) {
		 this(maquinaDTO.getId(), maquinaDTO.getNome(), maquinaDTO.getDepartamento(),
				 maquinaDTO.getObservacoes());
		}
	
	public Maquina(Integer id, String nome, String departamento, String observacoes) {
		super();
		this.id = id;
		this.nome = nome;
		this.departamento = departamento;
		this.observacoes = observacoes;
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

	public String getDepartamento() {
		return departamento;
	}

	public void setDepartamento(String departamento) {
		this.departamento = departamento;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(departamento, id, nome, observacoes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maquina other = (Maquina) obj;
		return Objects.equals(departamento, other.departamento) && Objects.equals(id, other.id)
				&& Objects.equals(nome, other.nome) && Objects.equals(observacoes, other.observacoes);
	}

}
