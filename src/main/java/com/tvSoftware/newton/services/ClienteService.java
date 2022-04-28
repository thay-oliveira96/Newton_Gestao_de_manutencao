package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Pessoa;
import com.tvSoftware.newton.domain.Cliente;
import com.tvSoftware.newton.domain.dtos.ClienteDTO;
import com.tvSoftware.newton.repositories.PessoaRepository;
import com.tvSoftware.newton.repositories.ClienteRepository;
import com.tvSoftware.newton.services.exceptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exceptions.ObjectnotFoundException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;

	public Cliente findById(Integer id) {
		Optional<Cliente> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	// Metodo de consultar todos os cadastro de tecnico
	public List<Cliente> findAll() {
		return repository.findAll();
	}

	// Metodo de Criar novos cadastro de tecnicos e manda salvar no banco
	public Cliente create(ClienteDTO objDTO) {
		objDTO.setId(null);
		validaPorCpfEEmail(objDTO);
		Cliente newObj = new Cliente(objDTO);
		return repository.save(newObj);
	}

	// Atualiza as informações do tecnico
	public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
		objDTO.setId(id);
		Cliente oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Cliente(objDTO);
		return repository.save(oldObj);
	}

	// Metodo para excluir tecnico
	public void delete(Integer id) {
		Cliente obj = findById(id);
		//Validação se o tecnico ja tem uma ordem de serviço
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Cliente possui ordens de Serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	// verifica se o CPF ou E-mail já existe no banco
	private void validaPorCpfEEmail(ClienteDTO objDTO) {
		Optional<Pessoa> obj = pessoaRepository.findByCpf(objDTO.getCpf());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("CPF ja cadastrado no Sistema");
		}

		// Busca por email para verificar se existe o objeto
		obj = pessoaRepository.findByEmail(objDTO.getEmail());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Email ja cadastrado no Sistema");
		}
	}

}
