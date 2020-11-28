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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	///CONSULTA TODOS
	@Secured({"ROLE_ADMIN", "ROLE_VICERRECTORIA", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR",  "ROLE_USUARIO"})
	@GetMapping("/comisiones-estados")
	public List<ComisionEstado>  index(){
		return comisionEstadoService.findAll();
	}
	
	///CONSULTA TODOS
	@Secured({"ROLE_ADMIN", "ROLE_VICERRECTORIA", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR",  "ROLE_USUARIO"})
	@GetMapping("/comisiones-estados/page/{page}")
	public Page<ComisionEstado> index(@PathVariable Integer page){
		return comisionEstadoService.findAll(PageRequest.of(page, 10));
	}
	
	//CONSULTA POR ID
	@Secured({"ROLE_ADMIN", "ROLE_VICERRECTORIA", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR",  "ROLE_USUARIO"})
	@GetMapping("/comisiones-estados/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		ComisionEstado comisionEstado =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			comisionEstado = comisionEstadoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (comisionEstado == null) {
			response.put("mensaje", "Error: La comisión y estado con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<ComisionEstado>(comisionEstado, HttpStatus.OK);
		
	}
	
	
	
	//CREA 
	@Secured({"ROLE_ADMIN", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR"})
	@PostMapping("/comisiones-estados")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody ComisionEstado comisionEstado, BindingResult result) {
		
		ComisionEstado comisionEstadoNuevo =  null; 
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
			comisionEstadoNuevo = comisionEstadoService.save(comisionEstado);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "La comisión y su estado ha sido creada con éxito!");
		response.put("comisionEstado", comisionEstadoNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	
	/*//ACTUALIZA
	@Secured({"ROLE_ADMIN", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR"})
	@PutMapping("/comisiones-estados/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody ComisionEstado comisionEstado, BindingResult result, @PathVariable Long id) {
		
		ComisionEstado comisionEstadoActual = comisionEstadoService.findById(id);  
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (comisionEstadoActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El usuario con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			comisionEstadoActual.setComision(comisionEstado.);
			
			usuarioActulizado = usuarioService.save(usuarioActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el usuario en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		
		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario", usuarioActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}*/
	
	

	//ELIMINA
	@Secured({"ROLE_ADMIN", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR"})
	@DeleteMapping("/comisiones-estados/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			comisionEstadoService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "No se pudo eliminar la comisión y su estado en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "La comisión y su estado ha sido eliminado con éxito!");
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.OK);
		
	}
	
	
}
