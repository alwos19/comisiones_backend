package com.udea.comisiones.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udea.comisiones.backend.apirest.models.entity.Departamento;
import com.udea.comisiones.backend.apirest.models.services.IDepartamentoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class DepartamentoRestController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	//
	
	@GetMapping("/departamentos")
	public List<Departamento> index(){
		return departamentoService.findAll();
	}
	
	@GetMapping("/departamentos/{id}")
	public Departamento show(@PathVariable Long id) {
		return departamentoService.findById(id);
	}
	
	@PostMapping("/departamentos")
	@ResponseStatus(HttpStatus.CREATED)
	public Departamento create(@RequestBody Departamento departamento) {
		return departamentoService.save(departamento);
	}
	
	@PutMapping("/departamentos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Departamento update(@RequestBody Departamento departamento, @PathVariable Long id) {
		Departamento departamentodActual = departamentoService.findById(id);
		
		departamentodActual.setNombre(departamento.getNombre());
		departamentodActual.setDescripcion(departamento.getDescripcion());
		
		return departamentoService.save(departamentodActual);
	}
	
	@DeleteMapping("/departamentos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		departamentoService.delete(id);
	}

}
