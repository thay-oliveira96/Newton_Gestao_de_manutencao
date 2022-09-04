package com.tvSoftware.newton.domain;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.tvSoftware.newton.domain.dtos.DepartamentosDTO;

@Entity
public class Departamentos implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String nome;
	
	/*@ManyToMany(mappedBy="departamentos")
	private List<Empresas> empresas;*/
	
	public Departamentos() {
		super();	
	}
	public Departamentos(DepartamentosDTO departamentosDTO) {
		 this(departamentosDTO.getId(), departamentosDTO.getNome());
		}
	
	public Departamentos(Integer id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
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
	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamentos other = (Departamentos) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
	
}
