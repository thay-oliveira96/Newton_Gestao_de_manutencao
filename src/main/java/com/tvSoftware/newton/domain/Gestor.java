package com.tvSoftware.newton.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tvSoftware.newton.domain.dtos.GestorDTO;
import com.tvSoftware.newton.domain.enums.Perfil;


/*
 * 	Classe tecnico
 * 	HErda os atributos da classe pessoa
 * 																							*/
@Entity
public class Gestor extends Pessoa {
	private static final long serialVersionUID = 1L;
	
	@JsonIgnore
	@OneToMany(mappedBy = "gestor") //O Gestor tem diversos chamados 
	private List<Chamado> chamados = new ArrayList<>();

	public Gestor() {
		super();
		addPerfil(Perfil.GESTOR);
	}

	public Gestor(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
		addPerfil(Perfil.GESTOR);
	}
	
	//Metodo de transferencia de dados
	public Gestor(GestorDTO obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCriacao = obj.getDataCriacao();
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	

}
