package com.udea.comisiones.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udea.comisiones.backend.apirest.models.entity.Estado;
import com.udea.comisiones.backend.apirest.models.services.IEstadoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EstadoRestController  {
	
	@Autowired
	private IEstadoService estadoService;
	
	//
	
	@GetMapping("/estados")
	public List<Estado> index(){
		return estadoService.findAll();
	}
	
	@GetMapping("/estados/{id}")
	public Estado show(@PathVariable Long id) {
		return estadoService.findById(id);
	}
	
	@PostMapping("/estados")
	@ResponseStatus(HttpStatus.CREATED)
	public Estado create(@RequestBody Estado estado) {
		return estadoService.save(estado);
	}
	
	@PutMapping("/estados/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Estado update(@RequestBody Estado estado, @PathVariable Long id) {
		
		Estado estadoActual = estadoService.findById(id);
		
		estadoActual.setNombre(estado.getNombre());
		estadoActual.setDescripcion(estado.getDescripcion());
		
		return estadoService.save(estadoActual);
		
	}
	
	
	@GetMapping("/estados/filtrar-estados/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<Estado> filtrarEstados(@PathVariable String nombre) {
		return  estadoService.findByNombreIgnoreCase(nombre);
	}
	
	
			
}
