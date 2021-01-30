package com.mitocode.controller;

import java.net.URI;
import java.util.List;

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

import com.mitocode.model.Persona;
import com.mitocode.service.IPersonaService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/personas")
public class PersonaController {
	
	@Autowired
	private IPersonaService service;
	
	@GetMapping
	public ResponseEntity<List<Persona>> listar() {
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Persona> listarPorId(@PathVariable("id") Integer id) throws NotFoundException {
		Persona p = service.listarPorId(id);
		if (p == null) {
			throw new NotFoundException("El ID del path no existe");
		}
		
		return ResponseEntity.ok(p);
	}
	
	@PostMapping
	public ResponseEntity<Persona> registrar(@Valid @RequestBody Persona p) {
		Persona persona = service.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(persona.getIdPersona()).toUri();
		return ResponseEntity.created(location)
				.build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Persona> modificar(@Valid @RequestBody Persona p, @PathVariable("id") Integer id) throws Exception {
		Persona personaBD = service.listarPorId(id);
		if (personaBD == null) {
			throw new NotFoundException("El ID del path no existe");
		}
		
		p.setIdPersona(id);
		Persona persona = service.modificar(p);
		
		// URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.ok(persona);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws NotFoundException {
		Persona personaBD = service.listarPorId(id);
		if (personaBD == null) {
			throw new NotFoundException("El ID del path no existe");
		}
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
