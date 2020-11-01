package com.udea.comisiones.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;
import com.udea.comisiones.backend.apirest.models.services.IComisionEstadoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ComisionEstadoRestController {

	@Autowired
	private IComisionEstadoService comisionEstadoService;
	
	//
	
	@GetMapping("/comision-estado")
	public List<ComisionEstado>  index(){
		return comisionEstadoService.findAll();
	}
	
	@GetMapping("/comision-estado/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ComisionEstado show(@PathVariable Long id) {
		return comisionEstadoService.findById(id);
	}
	
	
	@DeleteMapping("/comision-estado/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		comisionEstadoService.delete(id);
	}
	
	
}
