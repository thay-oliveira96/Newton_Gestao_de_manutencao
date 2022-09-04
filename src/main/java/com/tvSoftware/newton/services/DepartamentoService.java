package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Departamentos;
import com.tvSoftware.newton.domain.dtos.DepartamentosDTO;
import com.tvSoftware.newton.repositories.DepartamentosRepository;
import com.tvSoftware.newton.services.exeptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class DepartamentoService {

	@Autowired
	private DepartamentosRepository repository;

	public Departamentos findById(Integer id) {
		Optional<Departamentos> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Departamentos> findAll() {
		return repository.findAll();
	}

	public Departamentos create(DepartamentosDTO objDTO) {
		objDTO.setId(null);
		validaNome(objDTO);
		Departamentos newObj = new Departamentos(objDTO);
		return repository.save(newObj);
	}
 
	public Departamentos update(Integer id, @Valid DepartamentosDTO objDTO) {
		objDTO.setId(id);
		Departamentos oldObj = findById(id);
		
		oldObj = new Departamentos(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Departamentos obj = findById(id);

		repository.deleteById(id);
	}

	private void validaNome(DepartamentosDTO objDTO) {
		Optional<Departamentos> obj = repository.findByNome(objDTO.getNome());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Há um departamento com esse mesmo nome já cadastrado!");
		}

	}

}
