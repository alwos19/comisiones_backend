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

import com.udea.comisiones.backend.apirest.models.entity.Departamento;
import com.udea.comisiones.backend.apirest.models.services.IDepartamentoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class DepartamentoRestController {
	
	@Autowired
	private IDepartamentoService departamentoService;
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/departamentos")
	public List<Departamento> index(){
		return departamentoService.findAll();
	}
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/departamentos/page/{page}")
	public Page<Departamento> index(@PathVariable Integer page){
		return departamentoService.findAll(PageRequest.of(page, 10));
	}
	
	//CONSULTA POR ID
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/departamentos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Departamento departamento =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			departamento = departamentoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (departamento == null) {
			response.put("mensaje", "Error: El departamento con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
	}
	
	//CREA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@PostMapping("/departamentos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Departamento departamento, BindingResult result) {
		
		Departamento departamentoNuevo =  null; 
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
			departamentoNuevo = departamentoService.save(departamento);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El departamento ha sido creado con éxito!");
		response.put("departamento", departamentoNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@PutMapping("/departamentos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Departamento departamento, BindingResult result, @PathVariable Long id) {
		
		Departamento departamentoActual = departamentoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Departamento departamentoActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (departamentoActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El departamento con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			departamentoActual.setNombre(departamento.getNombre());
			departamentoActual.setDescripcion(departamento.getDescripcion());
			
			departamentoActulizado = departamentoService.save(departamentoActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el departamento en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		
		response.put("mensaje", "El departamento ha sido actualizado con éxito!");
		response.put("departamento", departamentoActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	//FILTRA POR NOMBRE
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@GetMapping("/departamentos/filtrar-nombre-departamentos/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filtrarDepartamentos(@PathVariable String nombre) {
		
		Departamento departamento =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			departamento = departamentoService.findByNombreIgnoreCase(nombre);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (departamento == null) {
			response.put("mensaje", "Error: El departamento con el nombre: ".concat(nombre.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Departamento>(departamento, HttpStatus.OK);
	} 
	
}
