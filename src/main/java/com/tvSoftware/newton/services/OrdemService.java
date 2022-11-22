package com.tvSoftware.newton.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Ordem;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.Defeitos;
import com.tvSoftware.newton.domain.Gestor;
import com.tvSoftware.newton.domain.Maquina;
import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.dtos.OrdemDTO;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.repositories.OrdemRepository;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class OrdemService {

	@Autowired
	private OrdemRepository repository;
	@Autowired
	private TecnicoService tecnicoService;
	@Autowired
	private ClienteService clienteService;
	@Autowired
	private GestorService gestorService;
	@Autowired
	private DefeitoService defeitoService;
	@Autowired
	private MaquinaService maquinaService;
	
	EmailService emailService = new EmailService();

	public Ordem findById(Integer id) {
		Optional<Ordem> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Ordem> findAll() {
		return repository.findAll();
	}

	public Ordem create(OrdemDTO obj) {
		Ordem ordem = repository.save(newChamado(obj));
		Ordem chamadoEmail = new Ordem();
		chamadoEmail.setId(ordem.getId());
		chamadoEmail.setDataAbertura(ordem.getDataAbertura());
		chamadoEmail.setCategoriaManutencao(ordem.getCategoriaManutencao());
		chamadoEmail.setDefeitos(ordem.getDefeitos());
		chamadoEmail.setMaquina(ordem.getMaquina());
		chamadoEmail.setParada(ordem.getParada());
		//emailService.enviar(chamadoEmail, "thaynanrodrigues96@gmail.com");
		return ordem;
	}

	public Ordem update(Integer id, @Valid OrdemDTO objDTO) {
		objDTO.setId(id);
		Ordem oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repository.save(oldObj);
	}

	private Ordem newChamado(OrdemDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		Gestor gestor = gestorService.findById(obj.getGestor());
		Defeitos defeitos = defeitoService.findById(obj.getDefeitos());
		Maquina maquina = maquinaService.findById(obj.getMaquina());
		
		Ordem ordem = new Ordem();
		if(obj.getId() != null) {
			ordem.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			ordem.setDataFechamento(LocalDate.now());
		}
		
		
		ordem.setTecnico(tecnico);
		ordem.setCliente(cliente);
		ordem.setGestor(gestor);
		ordem.setMaquina(maquina);
		ordem.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		ordem.setStatus(Status.toEnum(obj.getStatus()));
		ordem.setTipoManutencao(obj.getTipoManutencao());
		ordem.setCategoriaManutencao(obj.getCategoriaManutencao());
		ordem.setDefeitos(defeitos);
		ordem.setObservacoes(obj.getObservacoes());
		ordem.setObsTec(obj.getObsTec());
		ordem.setParada(obj.getParada());
		ordem.setHoraParada(obj.getHoraParada());
		
		return ordem;
	}

}
















