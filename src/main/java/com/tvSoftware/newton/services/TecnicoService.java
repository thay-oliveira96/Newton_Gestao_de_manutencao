package com.tvSoftware.newton.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tvSoftware.newton.domain.Tecnico;
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
}
