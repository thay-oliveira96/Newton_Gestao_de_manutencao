package com.tvSoftware.newton.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.dtos.TecnicoDTO;
import com.tvSoftware.newton.repositories.TecnicoRepository;
import com.tvSoftware.newton.services.exceptions.ObjectnotFoundException;

@Service
public class TecnicoService {

		@Autowired
		private TecnicoRepository repository;
		
		public Tecnico findById(Integer id) {
			Optional<Tecnico> obj = repository.findById(id);
			return obj.orElseThrow(() -> new ObjectnotFoundException("Objeto não encontrado! Id: " + id));
		}
		
		//Metodo de consultar todos os cadastro de tecnico
		public List<Tecnico> findAll() {
			return repository.findAll();
		}
		//Metodo de Criar novos cadastro de tecnicos
		public Tecnico create(TecnicoDTO objDTO) {
			objDTO.setId(null);
			Tecnico newObj = new Tecnico(objDTO);
			return repository.save(newObj);
		}
}
