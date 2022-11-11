package com.tvSoftware.newton.services;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Chamado;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.Defeitos;
import com.tvSoftware.newton.domain.Gestor;
import com.tvSoftware.newton.domain.Maquina;
import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.dtos.ChamadoDTO;
import com.tvSoftware.newton.domain.enums.Prioridade;
import com.tvSoftware.newton.domain.enums.Status;
import com.tvSoftware.newton.repositories.ChamadoRepository;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class ChamadoService {

	@Autowired
	private ChamadoRepository repository;
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

	public Chamado findById(Integer id) {
		Optional<Chamado> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! ID: " + id));
	}

	public List<Chamado> findAll() {
		return repository.findAll();
	}

	public Chamado create(ChamadoDTO obj) {
		Chamado chamado = repository.save(newChamado(obj));
		Chamado chamadoEmail = new Chamado();
		chamadoEmail.setId(chamado.getId());
		chamadoEmail.setPrioridade(chamado.getPrioridade());
		chamadoEmail.setStatus(chamado.getStatus());
		chamadoEmail.setDataAbertura(chamado.getDataAbertura());
		emailService.enviar(chamadoEmail, "thaynanrodrigues96@gmail.com");
		return chamado;
	}

	public Chamado update(Integer id, @Valid ChamadoDTO objDTO) {
		objDTO.setId(id);
		Chamado oldObj = findById(id);
		oldObj = newChamado(objDTO);
		return repository.save(oldObj);
	}

	private Chamado newChamado(ChamadoDTO obj) {
		Tecnico tecnico = tecnicoService.findById(obj.getTecnico());
		Cliente cliente = clienteService.findById(obj.getCliente());
		Gestor gestor = gestorService.findById(obj.getGestor());
		Defeitos defeitos = defeitoService.findById(obj.getDefeitos());
		Maquina maquina = maquinaService.findById(obj.getMaquina());
		
		Chamado chamado = new Chamado();
		if(obj.getId() != null) {
			chamado.setId(obj.getId());
		}
		
		if(obj.getStatus().equals(2)) {
			chamado.setDataFechamento(LocalDate.now());
		}
		
		
		chamado.setTecnico(tecnico);
		chamado.setCliente(cliente);
		chamado.setGestor(gestor);
		chamado.setMaquina(maquina);
		chamado.setPrioridade(Prioridade.toEnum(obj.getPrioridade()));
		chamado.setStatus(Status.toEnum(obj.getStatus()));
		chamado.setTipoManutencao(obj.getTipoManutencao());
		chamado.setCategoriaManutencao(obj.getCategoriaManutencao());
		chamado.setDefeitos(defeitos);
		chamado.setObservacoes(obj.getObservacoes());
		chamado.setObsTec(obj.getObsTec());
		
		return chamado;
	}

}
















