package com.tvSoftware.newton.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tvSoftware.newton.domain.Gestor;
import com.tvSoftware.newton.domain.dtos.GestorDTO;
import com.tvSoftware.newton.services.GestorService;

@RestController
@RequestMapping(value = "/gestores") // requisão HTTP do Gestor
public class GestorResource {

	// private static final String objDTO = null;
	// localhost:8080/tecnicos ou servername/tecnicos

	@Autowired
	private GestorService service;

	@GetMapping(value = "/{id}") // Busca por Id busca apenas um tecnico para seu id
	public ResponseEntity<GestorDTO> findById(@PathVariable Integer id) {
		Gestor obj = service.findById(id);
		return ResponseEntity.ok().body(new GestorDTO(obj));
	}

	// Retorna a lista de Gestor DTO
	// Esse metodo sera chamado quando solocitar uma lista na URL sem parametro
	@GetMapping
	public ResponseEntity<List<GestorDTO>> findAll() {
		List<Gestor> list = service.findAll();
		List<GestorDTO> listDTO = list.stream().map(obj -> new GestorDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	// Comando Create criação de novos tecnicos
	@PostMapping
	public ResponseEntity<GestorDTO> crate(@Valid @RequestBody GestorDTO objDTO) {
		Gestor newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	// Metodo Update do Gestor, atualização de cadastro
	@PutMapping(value = "/{id}")
	public ResponseEntity<GestorDTO> update(@PathVariable Integer id, @RequestBody GestorDTO objDTO) {
		Gestor obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new GestorDTO(obj));
	}
	//Deletando o tecnico
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<GestorDTO> delete(@PathVariable Integer id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}

}
