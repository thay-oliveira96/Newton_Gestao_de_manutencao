package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Chamado;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.dtos.ChamadoDTO;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.repositories.ChamadoRepository;
import com.tvSoftware.newton.services.exceptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	//@Autowired
	//private GestorService gestorService;
	
	//Lista Chamados por Id
	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID:" + id));
	}
	
	
	//Lista Ordens de Serviço todos
	public List<Chamado> findAll() {
		return repository.findAll();
	}
	
	
	//Metodo de criação de Ordens de Serviço
	public Chamado create(@Valid ChamadoDTO objDTO) {
		return repository.save(newChamado(objDTO));
	} 
	
	//Metodo de Atualização de cadastro de ordens de manutenção
	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repository.save(oldObj);
	}
	
	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		//Gestor gestor = gestorService.findById(obj.getGestor());
		
		Chamado chamado = new Chamado();
		
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		//chamado.setGestor(gestor);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTitulo(obj.getTitulo());
		chamado.setObservacoes(obj.getObservacoes());
		return chamado;
	}
		
}
