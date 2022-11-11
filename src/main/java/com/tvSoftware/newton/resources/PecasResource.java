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

import com.tvSoftware.newton.domain.Pecas;
import com.tvSoftware.newton.domain.dtos.PecasDTO;
import com.tvSoftware.newton.services.PecaService;


@RestController
@RequestMapping(value = "/pecas")
public class PecasResource {

	@Autowired
	private PecaService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<PecasDTO> findById(@PathVariable Integer id) {
		Pecas obj = service.findById(id);
		return ResponseEntity.ok().body(new PecasDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<PecasDTO>> findAll() {
		List<Pecas> list = service.findAll();
		List<PecasDTO> listDTO = list.stream().map(obj -> new PecasDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<PecasDTO> create(@Valid @RequestBody PecasDTO objDTO) {
		Pecas newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<PecasDTO> update(@PathVariable Integer id, @Valid @RequestBody PecasDTO objDTO) {
		Pecas obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new PecasDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<PecasDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
 

















