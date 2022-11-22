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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tvSoftware.newton.domain.Ordem;
import com.tvSoftware.newton.domain.dtos.OrdemDTO;
import com.tvSoftware.newton.services.OrdemService;

@RestController
@RequestMapping(value = "/ordens")
public class OrdemResource {

	@Autowired
	private OrdemService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<OrdemDTO> findById(@PathVariable Integer id) {
		Ordem obj = service.findById(id);
		return ResponseEntity.ok().body(new OrdemDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<OrdemDTO>> findAll() {
		List<Ordem> list = service.findAll();
		List<OrdemDTO> listDTO = list.stream().map(obj -> new OrdemDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PostMapping
	public ResponseEntity<OrdemDTO> create(@Valid @RequestBody OrdemDTO obj) {
		Ordem newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<OrdemDTO> update(@PathVariable Integer id, @Valid @RequestBody OrdemDTO objDTO) {
		Ordem newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new OrdemDTO(newObj));
	}
}
