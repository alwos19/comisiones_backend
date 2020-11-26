package com.udea.comisiones.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
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

import com.udea.comisiones.backend.apirest.models.entity.Estado;
import com.udea.comisiones.backend.apirest.models.services.IEstadoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class EstadoRestController  {
	
	@Autowired
	private IEstadoService estadoService;
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/estados")
	public List<Estado> index(){
		return estadoService.findAll();
	}
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/estados/page/{page}")
	public Page<Estado> index(@PathVariable Integer page){
		return estadoService.findAll(PageRequest.of(page, 10));
	}
	
	//CONSULTA POR ID
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/estados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Estado estado =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			estado = estadoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (estado == null) {
			response.put("mensaje", "Error: El estado con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Estado>(estado, HttpStatus.OK);
	}
	
	//CREA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@PostMapping("/estados")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Estado estado, BindingResult result) {
		
		Estado estadoNuevo =  null; 
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
			estadoNuevo = estadoService.save(estado);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El estado ha sido creado con éxito!");
		response.put("estado", estadoNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@PutMapping("/estados/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Estado estado, BindingResult result, @PathVariable Long id) {
		
		Estado estadoActual  = estadoService.findById(id);  
		Map<String, Object> response = new HashMap<>();
		Estado estadoActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (estadoActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El estado con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			estadoActual.setNombre(estado.getNombre());
			estadoActual.setDescripcion(estado.getDescripcion());
			
			estadoActulizado = estadoService.save(estadoActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el estado en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		
		response.put("mensaje", "El estado ha sido actualizado con éxito!");
		response.put("estado", estadoActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
		
	}
	
	//FILTRA POR NOMBRE
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/estados/filtrar-estados/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filtrarEstados(@PathVariable String nombre) {
		
		Estado estado =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			estado = estadoService.findByNombreIgnoreCase(nombre);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (estado == null) {
			response.put("mensaje", "Error: El estado con el nombre: ".concat(nombre.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Estado>(estado, HttpStatus.OK);
	}
	
	
			
}
