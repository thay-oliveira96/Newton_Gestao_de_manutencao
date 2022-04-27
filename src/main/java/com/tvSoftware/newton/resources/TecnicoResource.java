package com.tvSoftware.newton.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tvSoftware.newton.domain.Tecnico;
import com.tvSoftware.newton.domain.dtos.TecnicoDTO;
import com.tvSoftware.newton.services.TecnicoService;

@RestController 
@RequestMapping(value = "/tecnicos") // requisão HTTP do Tecnico
public class TecnicoResource {
	
	// localhost:8080/tecnicos ou servername/tecnicos
	
	@Autowired
	private TecnicoService service;
	
	@GetMapping(value = "/{id}") //Busca por Id busca apenas um tecnico para seu id
	public ResponseEntity<TecnicoDTO> findById(@PathVariable Integer id) {
		Tecnico obj = service.findById(id);
		return ResponseEntity.ok().body(new TecnicoDTO(obj));
	}
	//Retorna a lista de Tecnico DTO
	//Esse metodo sera chamado quando solocitar uma lista na URL sem parametro
	@GetMapping
	public ResponseEntity<List<TecnicoDTO>> findAll() {
		List<Tecnico> list = service.findAll();
		List<TecnicoDTO> listDTO = list.stream().map(obj -> new TecnicoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}
	
	//Comando Create criação de novos tecnicos
	@PostMapping
	public ResponseEntity<TecnicoDTO> crate(@Valid @RequestBody TecnicoDTO objDTO){
		Tecnico newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	

}
