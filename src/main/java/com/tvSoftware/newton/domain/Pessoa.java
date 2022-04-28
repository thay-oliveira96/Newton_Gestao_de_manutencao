package com.tvSoftware.newton.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tvSoftware.newton.domain.enums.Perfil;
/*
 * O Obejto pessoa tem como objetivo Cadastrar pesssoas no banco de dados ele também recebera informações
 * de outras classe com Cliente, Tecnico, Gestor e administrador
 * */

//Entity apresenta como entidade e cria a tabela de banco de dados com o nome pessoa
@Entity
public abstract class Pessoa implements Serializable {
	private static final long serialVersionUID = 1L;
	//Utilizando o protect, pois esses atributos não podem ficar publicos, somente recebera das classes herdadas
	@Id //utiliza para dizer que o atributo ID abaixo é uma chave primaria
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Especifica quem vai gerar o ID é o banco ou seja para cada objeto o banco vai gerar um ID diferente
	protected Integer id;
	protected String nome;
	
	@CPF
	@Column(unique = true) // Especifica que a coluna CPF é unica no banco, não vai existir outra coluna com o mesmo valor
	protected String cpf;
	@Column(unique = true) // Especifica que a coluna E-mail é unica no banco, não vai existir outra coluna com o mesmo valor
	protected String email;
	protected String senha;
	
	@ElementCollection(fetch = FetchType.EAGER) //Lista usuarios do bd, quando der um get tras a lista com o usuário
	@CollectionTable(name = "PERFIS") //Cria Coleção de tabela somente com os perfis.
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy") //formata o horario para o padrão especificado
	protected LocalDate dataCriacao = LocalDate.now();
	
	//Metodo construtor!
	public Pessoa() {
		super();
		addPerfil(Perfil.CLIENTE);
		
	}
	// Super Classe pessoa, recebe variaveis
	public Pessoa(Integer id, String nome, String cpf, String email, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.email = email;
		this.senha = senha;
		addPerfil(Perfil.CLIENTE);
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<Perfil> getPerfis() {
		return perfis.stream().map(x -> Perfil.toEnum(x)).collect(Collectors.toSet());
	}

	public void addPerfil(Perfil perfil) {
		this.perfis.add(perfil.getCodigo());
	}

	public LocalDate getDataCriacao() {
		return dataCriacao;
	}

	public void setDataCriacao(LocalDate dataCriacao) {
		this.dataCriacao = dataCriacao;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pessoa other = (Pessoa) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id);
	}
	
	

}
