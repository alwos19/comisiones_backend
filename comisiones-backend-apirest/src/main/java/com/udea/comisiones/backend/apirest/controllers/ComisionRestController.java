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

import com.udea.comisiones.backend.apirest.models.entity.Comision;
import com.udea.comisiones.backend.apirest.models.services.IComisionService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class ComisionRestController {

	@Autowired
	private IComisionService comisionService;
	
	//
	
	@GetMapping("/comisiones")
	public List<Comision> index(){
		return comisionService.findAll();
	}
	
	@GetMapping("/comisiones/{id}")
	public Comision show(@PathVariable Long id) {
		return comisionService.findById(id);
	}
	
	@PostMapping("/comisiones")
	@ResponseStatus(HttpStatus.CREATED)
	public Comision create(@RequestBody Comision comision) {
		return comisionService.save(comision);
	}
	
	@PutMapping("/comisiones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Comision update(@RequestBody Comision comision, @PathVariable Long id) {
		Comision comisionActual = comisionService.findById(id);
		
		comisionActual.setIdioma(comision.getIdioma());
		comisionActual.setJustificacion(comision.getJustificacion());
		comisionActual.setLugar(comision.getLugar());
		comisionActual.setFechaInicio(comision.getFechaInicio());
		comisionActual.setFechaFin(comision.getFechaFin());
		comisionActual.setResolucion(comision.getResolucion());
		comisionActual.setRespuestaDevolucion(comision.getRespuestaDevolucion());
		comisionActual.setFechaResolucion(comision.getFechaResolucion());
		comisionActual.setFechaActulizacion(comision.getFechaActulizacion());
		
		return comisionService.save(comisionActual);
	}
	
	@DeleteMapping("/comisiones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		comisionService.delete(id);
	}
	

	@GetMapping("/comisiones/filtrar-lugar-comisiones/{lugar}")
	@ResponseStatus(HttpStatus.OK)
	public List<Comision> filtrarComisiones(@PathVariable String lugar) {
		return comisionService.findByLugarContainingIgnoreCase(lugar);	} 
	
	
}
