package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Pecas;
import com.tvSoftware.newton.domain.dtos.PecasDTO;
import com.tvSoftware.newton.repositories.PecasRepository;
import com.tvSoftware.newton.services.exeptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class PecaService {

	@Autowired
	private PecasRepository repository;

	public Pecas findById(Integer id) {
		Optional<Pecas> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Pecas> findAll() {
		return repository.findAll();
	}

	public Pecas create(PecasDTO objDTO) {
		objDTO.setId(null);
		validaNome(objDTO);
		Pecas newObj = new Pecas(objDTO);
		return repository.save(newObj);
	}
 
	public Pecas update(Integer id, @Valid PecasDTO objDTO) {
		objDTO.setId(id);
		Pecas oldObj = findById(id);
		
		oldObj = new Pecas(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}

	private void validaNome(PecasDTO objDTO) {
		Optional<Pecas> obj = repository.findByNome(objDTO.getNome());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Há um departamento com esse mesmo nome já cadastrado!");
		}

	}

}
