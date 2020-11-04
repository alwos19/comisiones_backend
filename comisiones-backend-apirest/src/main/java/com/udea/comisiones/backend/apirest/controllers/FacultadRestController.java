package com.udea.comisiones.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
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
	
	//CONSULTA TODOS
	@GetMapping("/facultades")
	public List<Facultad> index(){
		return facultadService.findAll();
	}
	
	//CONSULTA POR ID
	@GetMapping("/facultades/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Facultad facultad =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			facultad = facultadService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (facultad == null) {
			response.put("mensaje", "Error: La Facultad con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Facultad>(facultad, HttpStatus.OK); 
	}
	
	//CREA
	@PostMapping("/facultades")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid  @RequestBody Facultad facultad, BindingResult result) {
		
		Facultad facultadNuevo =  null; 
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		//---
		try {
			facultadNuevo = facultadService.save(facultad);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "La Facultad ha sido creada con éxito!");
		response.put("facultad", facultadNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	
	//ACTUALIZA
	@PutMapping("/facultades/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid  @RequestBody Facultad facultad, BindingResult result, @PathVariable Long id) {
		
		Facultad facultadActual = facultadService.findById(id); 
		Map<String, Object> response = new HashMap<>();
		Facultad facultadActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (facultadActual == null) {
			response.put("mensaje", "Error: No se puede Editar. La facultad con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			facultadActual.setNombre(facultad.getNombre());
			facultadActual.setCentroDeCosto(facultad.getCentroDeCosto());
			facultadActual.setDescripcion(facultad.getDescripcion());
			
			facultadActulizado = facultadService.save(facultadActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar la facultad  en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		response.put("mensaje", "La facultad ha sido actualizada con éxito!");
		response.put("facultad", facultadActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
		
	}
	
			
	//FILTRA POR NOMBRE
	@GetMapping("/facultades/filtrar-facultades/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<Facultad> filtrarFacultades(@PathVariable String nombre) {
		return facultadService.findByNombreIgnoreCase(nombre);
	} 
	
	
}
