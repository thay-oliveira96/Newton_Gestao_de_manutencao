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

import com.tvSoftware.newton.domain.Departamentos;
import com.tvSoftware.newton.domain.dtos.DepartamentosDTO;
import com.tvSoftware.newton.services.DepartamentoService;


@RestController
@RequestMapping(value = "/departamentos")
public class DepartamentosResource {

	@Autowired
	private DepartamentoService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<DepartamentosDTO> findById(@PathVariable Integer id) {
		Departamentos obj = service.findById(id);
		return ResponseEntity.ok().body(new DepartamentosDTO(obj));
	}

	@GetMapping
	public ResponseEntity<List<DepartamentosDTO>> findAll() {
		List<Departamentos> list = service.findAll();
		List<DepartamentosDTO> listDTO = list.stream().map(obj -> new DepartamentosDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@PostMapping
	public ResponseEntity<DepartamentosDTO> create(@Valid @RequestBody DepartamentosDTO objDTO) {
		Departamentos newObj = service.create(objDTO);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(newObj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@PutMapping(value = "/{id}")
	public ResponseEntity<DepartamentosDTO> update(@PathVariable Integer id, @Valid @RequestBody DepartamentosDTO objDTO) {
		Departamentos obj = service.update(id, objDTO);
		return ResponseEntity.ok().body(new DepartamentosDTO(obj));
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DepartamentosDTO> delete(@PathVariable Integer id) {
		service.delete(id); 
		return ResponseEntity.noContent().build();
	}

}
 

















