package com.tvSoftware.newton.domain;

import java.util.ArrayList;
import java.util.List;

/*
 * Objeto cliente 
 *Esse objeto Herda os atributos da Classe Pessoa
 * */

//Utiliza-se extends para herdar a classe cliente
public class Cliente extends Pessoa {
	
	private List<Chamado> chamados = new ArrayList<>();

	public Cliente() {
		super();
	}

	public Cliente(Integer id, String nome, String cpf, String email, String senha) {
		super(id, nome, cpf, email, senha);
	}

	public List<Chamado> getChamados() {
		return chamados;
	}

	public void setChamados(List<Chamado> chamados) {
		this.chamados = chamados;
	}
	
	
}
