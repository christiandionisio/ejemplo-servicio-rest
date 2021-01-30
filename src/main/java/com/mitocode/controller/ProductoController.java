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

import com.mitocode.model.Producto;
import com.mitocode.service.IProductoService;

import javassist.NotFoundException;

@RestController
@RequestMapping("/productos")
public class ProductoController {
	
	@Autowired
	private IProductoService service;
	
	@GetMapping
	public ResponseEntity<List<Producto>> listar() {
		return ResponseEntity.ok(service.listar());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Producto> listarPorId(@PathVariable("id") Integer id) throws NotFoundException {
		Producto p = service.listarPorId(id);
		if (p == null) {
			throw new NotFoundException("El ID del path no existe");
		}
		
		return ResponseEntity.ok(p);
	}
	
	@PostMapping
	public ResponseEntity<Producto> registrar(@Valid @RequestBody Producto p) {
		Producto producto = service.registrar(p);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(producto.getIdProducto()).toUri();
		return ResponseEntity.created(location)
				.build();
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Producto> modificar(@Valid @RequestBody Producto p, @PathVariable("id") Integer id) throws Exception {
		Producto personaBD = service.listarPorId(id);
		if (personaBD == null) {
			throw new NotFoundException("El ID del path no existe");
		}
		
		p.setIdProducto(id);
		Producto persona = service.modificar(p);
		
		// URI location = ServletUriComponentsBuilder.fromCurrentRequest().build().toUri();
		return ResponseEntity.ok(persona);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> eliminar(@PathVariable("id") Integer id) throws NotFoundException {
		Producto personaBD = service.listarPorId(id);
		if (personaBD == null) {
			throw new NotFoundException("El ID del path no existe");
		}
		service.eliminar(id);
		return ResponseEntity.noContent().build();
	}

}
