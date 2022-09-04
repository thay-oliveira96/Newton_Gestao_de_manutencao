package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Maquina;
import com.tvSoftware.newton.domain.dtos.MaquinaDTO;
import com.tvSoftware.newton.repositories.MaquinaRepository;
import com.tvSoftware.newton.services.exeptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class MaquinaService {

	@Autowired
	private MaquinaRepository repository;

	public Maquina findById(Integer id) {
		Optional<Maquina> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Maquina> findAll() {
		return repository.findAll();
	}

	public Maquina create(MaquinaDTO objDTO) {
		objDTO.setId(null);
		validaNome(objDTO);
		Maquina newObj = new Maquina(objDTO);
		return repository.save(newObj);
	}
 
	public Maquina update(Integer id, @Valid MaquinaDTO objDTO) {
		objDTO.setId(id);
		Maquina oldObj = findById(id);
		
		oldObj = new Maquina(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Maquina obj = findById(id);

		
		repository.deleteById(id);
	}

	private void validaNome(MaquinaDTO objDTO) {
		Optional<Maquina> obj = repository.findByNome(objDTO.getNome());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Há um departamento com esse mesmo nome já cadastrado!");
		}

	}

}















