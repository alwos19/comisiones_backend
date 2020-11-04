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

import com.udea.comisiones.backend.apirest.models.entity.Rol;
import com.udea.comisiones.backend.apirest.models.services.IRolService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class RolRestController {

	@Autowired
	private IRolService rolService;
	
	//CONSULTA TODOS 
	@GetMapping("/roles")
	public List<Rol> index(){
		return rolService.findAll();
	}
	
	//CONSULTA POR ID
	@GetMapping("/roles/{id}")
	public ResponseEntity<?>  show(@PathVariable Long id) {
		Rol rol =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			rol = rolService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (rol == null) {
			response.put("mensaje", "Error: El rol con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Rol>(rol, HttpStatus.OK);
	}
	
	
	//CREA 
	@PostMapping("/roles")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Rol rol, BindingResult result) {
		
		Rol rolNuevo =  null; 
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
			rolNuevo = rolService.save(rol);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El rol ha sido creado con éxito!");
		response.put("rol", rolNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
		
	}
	
	//ACTUALIZA
	@PutMapping("/roles/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Rol rol, BindingResult result, @PathVariable Long id) {
		
		Rol rolActual = rolService.findById(id); 
		Map<String, Object> response = new HashMap<>();
		Rol rolActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (rolActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El Rol con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			rolActual.setNombre(rol.getNombre());
			rolActual.setDescripcion(rol.getDescripcion());
			
			rolActulizado = rolService.save(rolActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el Rol en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El Rol ha sido actualizado con éxito!");
		response.put("rol", rolActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	//FILTRA POR NOMBRE
	@GetMapping("/roles/filtrar-roles/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<Rol> filtrarRoles(@PathVariable String nombre) {
		return rolService.findByNombreIgnoreCase(nombre);
	} 

}
