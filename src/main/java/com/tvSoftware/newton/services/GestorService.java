package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Gestor;
import com.tvSoftware.newton.domain.Pessoa;
import com.tvSoftware.newton.domain.dtos.GestorDTO;
import com.tvSoftware.newton.repositories.GestorRepository;
import com.tvSoftware.newton.repositories.PessoaRepository;
import com.tvSoftware.newton.services.exeptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class GestorService {

	@Autowired
	private GestorRepository repository;
	@Autowired
	private PessoaRepository pessoaRepository;
	@Autowired
	private BCryptPasswordEncoder encoder;

	public Gestor findById(Integer id) {
		Optional<Gestor> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	// Metodo de consultar todos os cadastro de tecnico
	public List<Gestor> findAll() {
		return repository.findAll();
	}

	// Metodo de Criar novos cadastro de tecnicos e manda salvar no banco
	public Gestor create(GestorDTO objDTO) {
		objDTO.setId(null);
		objDTO.setSenha(encoder.encode(objDTO.getSenha()));
		validaPorCpfEEmail(objDTO);
		Gestor newObj = new Gestor(objDTO);
		return repository.save(newObj);
	}

	// Atualiza as informações do tecnico
	public Gestor update(Integer id, @Valid GestorDTO objDTO) {
		objDTO.setId(id);
		Gestor oldObj = findById(id);
		validaPorCpfEEmail(objDTO);
		oldObj = new Gestor(objDTO);
		return repository.save(oldObj);
	}

	// Metodo para excluir tecnico
	public void delete(Integer id) {
		Gestor obj = findById(id);
		//Validação se o tecnico ja tem uma ordem de serviço
		if(obj.getChamados().size() > 0) {
			throw new DataIntegrityViolationException("Gestor possui ordens de Serviço e não pode ser deletado!");
		}
		repository.deleteById(id);
	}

	// verifica se o CPF ou E-mail já existe no banco
	private void validaPorCpfEEmail(GestorDTO objDTO) {
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
