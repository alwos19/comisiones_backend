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

import com.udea.comisiones.backend.apirest.models.entity.Facultad;
import com.udea.comisiones.backend.apirest.models.services.IFacultadService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class FacultadRestController {

	@Autowired
	private IFacultadService facultadService;
	
	//
	
	@GetMapping("/facultades")
	public List<Facultad> index(){
		return facultadService.findAll();
	}
	
	@GetMapping("/facultades/{id}")
	public Facultad show(@PathVariable Long id) {
		return facultadService.findById(id);
	}
	
	@PostMapping("/facultades")
	@ResponseStatus(HttpStatus.CREATED)
	public Facultad create(@RequestBody Facultad facultad) {
		return facultadService.save(facultad);
	}
	
	@PutMapping("/facultades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Facultad update(@RequestBody Facultad facultad, @PathVariable Long id) {
		Facultad facultadActual = facultadService.findById(id);
		
		facultadActual.setNombre(facultad.getNombre());
		facultadActual.setCentroDeCosto(facultad.getCentroDeCosto());
		facultadActual.setDescripcion(facultad.getDescripcion());
		
		return facultadService.save(facultadActual);
	}
	
	@DeleteMapping("/facultades/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		facultadService.delete(id);
	}
	
	
}
