package com.tvSoftware.newton.domain;

import java.io.Serializable;
import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tvSoftware.newton.domain.enums.CategoriaManutencao;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.domain.enums.TipoManutencao;

@Entity
public class Chamado implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataAbertura = LocalDate.now();
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataFechamento;
	
	private Prioridade prioridade;
	
	private Status status;
	
	@Enumerated(EnumType.STRING)
	private TipoManutencao tipoManutencao;
	@Enumerated(EnumType.STRING)
	private CategoriaManutencao categoriaManutencao;
	
	@ManyToOne
	@JoinColumn(name = "defeitos_id")
	private Defeitos defeitos;
	
	private String observacoes;
	private String obsTec;
	
	@ManyToOne
	@JoinColumn(name = "maquina_id")
	private Maquina maquina;

	@ManyToOne
	@JoinColumn(name = "tecnico_id")
	private Tecnico tecnico;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;
	
	@ManyToOne
	@JoinColumn(name = "gestor_id")
	private Gestor gestor;


	public Chamado() {
		super();
	}

	public Chamado(Integer id, Prioridade prioridade, Status status,
			TipoManutencao tipoManutencao, CategoriaManutencao categoriaManutencao, 
			Defeitos defeitos, String observacoes, String obsTec, Tecnico tecnico,
			Cliente cliente, Gestor gestor, Maquina maquina) {
		super();
		this.id = id;
		this.prioridade = prioridade;
		this.status = status;
		this.tipoManutencao = tipoManutencao;
		this.categoriaManutencao = categoriaManutencao;
		this.defeitos = defeitos;
		this.observacoes = observacoes;
		this.obsTec = obsTec;
		this.tecnico = tecnico;
		this.cliente = cliente;
		this.gestor = gestor;
		this.maquina = maquina;
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

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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

	public Defeitos getDefeitos() {
		return defeitos;
	}

	public void setDefeitos(Defeitos defeitos) {
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

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Gestor getGestor() {
		return gestor;
	}

	public void setGestor(Gestor gestor) {
		this.gestor = gestor;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Chamado other = (Chamado) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
