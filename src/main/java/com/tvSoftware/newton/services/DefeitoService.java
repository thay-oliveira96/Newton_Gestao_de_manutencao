package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Defeitos;
import com.tvSoftware.newton.domain.dtos.DefeitosDTO;
import com.tvSoftware.newton.repositories.DefeitosRepository;
import com.tvSoftware.newton.services.exeptions.DataIntegrityViolationException;
import com.tvSoftware.newton.services.exeptions.ObjectnotFoundException;

@Service
public class DefeitoService {

	@Autowired
	private DefeitosRepository repository;

	public Defeitos findById(Integer id) {
		Optional<Defeitos> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
	}

	public List<Defeitos> findAll() {
		return repository.findAll();
	}

	public Defeitos create(DefeitosDTO objDTO) {
		objDTO.setId(null);
		validaDescricao(objDTO);
		Defeitos newObj = new Defeitos(objDTO);
		return repository.save(newObj);
	}
 
	public Defeitos update(Integer id, @Valid DefeitosDTO objDTO) {
		objDTO.setId(id);
		Defeitos oldObj = findById(id);
		
		oldObj = new Defeitos(objDTO);
		return repository.save(oldObj);
	}

	public void delete(Integer id) {
		Defeitos obj = findById(id);

		repository.deleteById(id);
	}

	private void validaDescricao(DefeitosDTO objDTO) {
		Optional<Defeitos> obj = repository.findByDescricao(objDTO.getDescricao());
		if (obj.isPresent() && obj.get().getId() != objDTO.getId()) {
			throw new DataIntegrityViolationException("Há um defeito com esse mesmo nome já cadastrado!");
		}

	}

}
