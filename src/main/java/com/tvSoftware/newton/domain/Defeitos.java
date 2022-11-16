package com.tvSoftware.newton.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tvSoftware.newton.domain.dtos.DefeitosDTO;

@Entity
public class Defeitos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String descricao;
	
	public Defeitos() {
		super();	
	}
	public Defeitos(DefeitosDTO defeitosDTO) {
		 this(defeitosDTO.getId(), defeitosDTO.getDescricao());
		}
	
	public Defeitos(Integer id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
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
	
	@Override
	public String toString() {
		return descricao;
	}
	
	@Override
	public int hashCode() {
		return Objects.hash(id, descricao);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Defeitos other = (Defeitos) obj;
		return Objects.equals(id, other.id) && Objects.equals(descricao, other.descricao);
	}
	
}
