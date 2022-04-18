package com.tvSoftware.newton.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.services.TecnicoService;

@RestController 
@RequestMapping(value = "/tecnicos") // requisão HTTP do Tecnico
public class TecnicoResource {
	
	// localhost:8080/tecnicos ou servername/tecnicos
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}") //Busca por Id busca apenas um tecnico para seu id
	public ResponseEntity<Tecnico> findById(@PathVariable Integer id) {
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(obj);
	}

}
