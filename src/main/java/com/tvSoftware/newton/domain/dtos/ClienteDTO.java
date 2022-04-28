package com.tvSoftware.newton.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.enums.Perfil;
/*
 * 
 * Essa classe tem como objetivo filtrar o que será apresentado nas requisições
 * 
 * */
public class ClienteDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected Integer id;
	@NotNull(message = "O campo Nome é Requerido")
	protected String nome;
	@NotNull(message = "O campo CPF é Requerido")
	protected String cpf;
	@NotNull(message = "O campo E-mail é Requerido")
	protected String email;
	@NotNull(message = "O campo Senha é Requerido")
	protected String senha;
	protected Set<Integer> perfis = new HashSet<>();
	
	@JsonFormat(pattern = "dd/MM/yyyy")
	protected LocalDate dataCricao = LocalDate.now();

	public ClienteDTO() {
		super();
		addPerfil(Perfil.CLIENTE);
	}

	public ClienteDTO(Cliente obj) {
		super();
		this.id = obj.getId();
		this.nome = obj.getNome();
		this.cpf = obj.getCpf();
		this.email = obj.getEmail();
		this.senha = obj.getSenha();
		this.perfis = obj.getPerfis().stream().map(x -> x.getCodigo()).collect(Collectors.toSet());
		this.dataCricao = obj.getDataCriacao();
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
		return dataCricao;
	}

	public void setDataCriacao(LocalDate dataCricao) {
		this.dataCricao = dataCricao;
	}
	
	
}
