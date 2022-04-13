package com.tvSoftware.newton.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.tvSoftware.newton.domain.enums.Perfil;


/*
 * 	Classe tecnico
 * 	HErda os atributos da classe pessoa
 * 																							*/
@Entity
public class Tecnico extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	//O tecnico tem diversos chamados 
	@OneToMany(mappedBy = "tecnico")
	private List<Chamado> chamados = new ArrayList<>();

	public Tecnico() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
