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

import com.tvSoftware.newton.domain.Defeitos;
import com.tvSoftware.newton.domain.dtos.DefeitosDTO;
import com.tvSoftware.newton.services.DefeitoService;


@RestController
@RequestMapping(value = "/defeitos")
public class DefeitosResource {

	@Autowired
	private DefeitoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<DefeitosDTO> findById(@PathVariable Integer id) {
		Defeitos obj = service.findById(id);
		return ResponseEntity.ok().body(new DefeitosDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<DefeitosDTO>> findAll() {
		List<Defeitos> list = service.findAll();
		List<DefeitosDTO> listDTO = list.stream().map(obj -> new DefeitosDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<DefeitosDTO> create(@Valid @RequestBody DefeitosDTO objDTO) {
		Defeitos newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<DefeitosDTO> update(@PathVariable Integer id, @Valid @RequestBody DefeitosDTO objDTO) {
		Defeitos obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new DefeitosDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DefeitosDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
 

















