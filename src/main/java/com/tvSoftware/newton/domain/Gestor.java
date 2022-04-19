package com.tvSoftware.newton.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvSoftware.newton.domain.enums.Perfil;


/*
 * 	Classe Gestor
 * 	Herda os atributos da classe pessoa
 * 																							*/
@Entity
public class Gestor extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "gestor") //O tecnico tem diversos chamados 
	private List<Chamado> chamados = new ArrayList<>();

	public Gestor() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Gestor(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.CLIENTE);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
