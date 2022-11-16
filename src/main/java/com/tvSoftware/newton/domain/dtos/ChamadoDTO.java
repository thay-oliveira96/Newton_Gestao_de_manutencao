package com.tvSoftware.newton.domain.dtos;

import java.io.Serializable;
import java.time.LocalDate;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tvSoftware.newton.domain.Chamado;
import com.tvSoftware.newton.domain.enums.CategoriaManutencao;
import com.tvSoftware.newton.domain.enums.TipoManutencao;

public class ChamadoDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Integer id;
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	//@NotNull(message = "O campo PRIORIDADE é requerido")
	private Integer prioridade;
	//@NotNull(message = "O campo STATUS é requerido")
	private Integer status;
	//@NotNull(message = "O campo Tipo de Manutencao é requerido")
	private TipoManutencao tipoManutencao;
	//@NotNull(message = "O campo Categoria de Manutenção é requerido")
	private CategoriaManutencao categoriaManutencao;
	@NotNull(message = "O campo TITULO é requerido")
	private Integer defeitos;
	@NotNull(message = "O campo OBSERVAÇÕES é requerido")
	private String observacoes;
	//@NotNull(message = "O campo OBSERVAÇÃO DO TECNICO é requerido")
	private String obsTec;
	//@NotNull(message = "O campo TECNICO é requerido")
	private Integer tecnico;
	@NotNull(message = "O campo CLIENTE é requerido")
	private Integer cliente;
	//@NotNull(message = "O campo Gestor é requerido")
	private Integer gestor;
	@NotNull(message = "O campo Maquina é requerido")
	private Integer maquina;
	private String nomeTecnico;
	private String nomeCliente;
	private String nomeGestor;
	private String nomeMaquina;
	private String nomeDefeitos;
	@NotNull(message = "Informe se a maquina está parada")
	private String parada;
	private String horaParada;

	public ChamadoDTO() {
		super();
	}

	public ChamadoDTO(Chamado obj) {
		this.id = obj.getId();
		this.dataAbertura = obj.getDataAbertura();
		this.dataFechamento = obj.getDataFechamento();
		this.prioridade = obj.getPrioridade().getCodigo();
		this.status = obj.getStatus().getCodigo();
		this.tipoManutencao = obj.getTipoManutencao();
		this.categoriaManutencao = obj.getCategoriaManutencao();
		this.defeitos = obj.getDefeitos().getId();
		this.observacoes = obj.getObservacoes();
		this.obsTec = obj.getObsTec();
		this.tecnico = obj.getTecnico().getId();
		this.cliente = obj.getCliente().getId();
		this.maquina = obj.getMaquina().getId();
		this.nomeCliente = obj.getCliente().getNome();
		this.nomeTecnico = obj.getTecnico().getNome();
		this.nomeGestor= obj.getGestor().getNome();
		this.nomeMaquina = obj.getMaquina().getNome();
		this.nomeDefeitos = obj.getDefeitos().getDescricao();
		this.parada = obj.getParada();
		this.horaParada = obj.getHoraParada();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDate getDataAbertura() {
		return dataAbertura;
	}

	public void setDataAbertura(LocalDate dataAbertura) {
		this.dataAbertura = dataAbertura;
	}

	public LocalDate getDataFechamento() {
		return dataFechamento;
	}

	public void setDataFechamento(LocalDate dataFechamento) {
		this.dataFechamento = dataFechamento;
	}

	public Integer getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Integer prioridade) {
		this.prioridade = prioridade;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
	
	public TipoManutencao getTipoManutencao() {
		return tipoManutencao;
	}

	public void setTipoManutencao(TipoManutencao tipoManutencao) {
		this.tipoManutencao = tipoManutencao;
	}

	public CategoriaManutencao getCategoriaManutencao() {
		return categoriaManutencao;
	}

	public void setCategoriaManutencao(CategoriaManutencao categoriaManutencao) {
		this.categoriaManutencao = categoriaManutencao;
	}

	public Integer getDefeitos() {
		return defeitos;
	}

	public void setDefeitos(Integer defeitos) {
		this.defeitos = defeitos;
	}

	public String getObservacoes() {
		return observacoes;
	}

	public void setObservacoes(String observacoes) {
		this.observacoes = observacoes;
	}
	
	public String getObsTec() {
		return obsTec;
	}

	public void setObsTec(String obsTec) {
		this.obsTec = obsTec;
	}

	public Integer getTecnico() {
		return tecnico;
	}

	public void setTecnico(Integer tecnico) {
		this.tecnico = tecnico;
	}

	public Integer getCliente() {
		return cliente;
	}

	public void setCliente(Integer cliente) {
		this.cliente = cliente;
	}
	
	public Integer getGestor() {
		return gestor;
	}

	public void setGestor(Integer gestor) {
		this.gestor = gestor;
	}
	
	public Integer getMaquina() {
		return maquina;
	}

	public void setMaquina(Integer maquina) {
		this.maquina = maquina;
	}

	public String getNomeTecnico() {
		return nomeTecnico;
	}

	public void setNomeTecnico(String nomeTecnico) {
		this.nomeTecnico = nomeTecnico;
	}

	public String getNomeCliente() {
		return nomeCliente;
	}

	public void setNomeCliente(String nomeCliente) {
		this.nomeCliente = nomeCliente;
	}
	
	public String getNomeGestor() {
		return nomeGestor;
	}
	
	public void setNomeGestor(String nomeGestor) {
		this.nomeGestor = nomeGestor;
	}
	
	public String getNomeMaquina() {
		return nomeMaquina;
	}
	
	public void setNomeMaquina(String nomeMaquina) {
		this.nomeMaquina = nomeMaquina;
	}
	
	public String getNomeDefeitos() {
		return nomeDefeitos;
	}
	
	public void setNomeDefeitos(String nomeDefeitos) {
		this.nomeDefeitos = nomeDefeitos;
	}

	public String getParada() {
		return parada;
	}

	public void setParada(String parada) {
		this.parada = parada;
	}

	public String getHoraParada() {
		return horaParada;
	}

	public void setHoraParada(String horaParada) {
		this.horaParada = horaParada;
	}
	
	
	
	

}
