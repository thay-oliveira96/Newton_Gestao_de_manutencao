package com.tvSoftware.newton.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.tvSoftware.newton.domain.Maquina;
import com.tvSoftware.newton.domain.dtos.MaquinaDTO;
import com.tvSoftware.newton.services.MaquinaService;

@RestController
@RequestMapping(value = "/maquinas")
public class MaquinaResource {

	@Autowired
	private MaquinaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<MaquinaDTO> findById(@PathVariable Integer id) {
		Maquina obj = service.findById(id);
		return ResponseEntity.ok().body(new MaquinaDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<MaquinaDTO>> findAll() {
		List<Maquina> list = service.findAll();
		List<MaquinaDTO> listDTO = list.stream().map(obj -> new MaquinaDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<MaquinaDTO> create(@Valid @RequestBody MaquinaDTO obj) {
		Maquina newObj = service.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<MaquinaDTO> update(@PathVariable Integer id, @Valid @RequestBody MaquinaDTO objDTO) {
		Maquina newObj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new MaquinaDTO(newObj));
	}
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<MaquinaDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}
}
