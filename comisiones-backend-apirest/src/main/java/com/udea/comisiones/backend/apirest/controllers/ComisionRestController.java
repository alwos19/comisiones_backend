package com.udea.comisiones.backend.apirest.controllers;

import java.util.ArrayList;
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
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/comisiones")
	public List<Comision> index(){
		return comisionService.findAll();
	}
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/comisiones/page/{page}")
	public Page <Comision> index(@PathVariable Integer page){
		return comisionService.findAll(PageRequest.of(page, 10));
	}
	
	//CONSULTA POR ID
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/comisiones/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Comision comision =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			comision = comisionService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (comision == null) {
			response.put("mensaje", "Error: La comision con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Comision>(comision, HttpStatus.OK);
	}
	
	//CREA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@PostMapping("/comisiones")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  create(@Valid @RequestBody Comision comision, BindingResult result) {
		
		Comision comisionNuevo =  null; 
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
			comisionNuevo = comisionService.save(comision);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "La comisión ha sido creada con éxito!");
		response.put("comision", comisionNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@PutMapping("/comisiones/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?>  update(@Valid @RequestBody Comision comision, BindingResult result, @PathVariable Long id) {
		
		Comision comisionActual = comisionService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Comision comisionActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (comisionActual == null) {
			response.put("mensaje", "Error: No se puede Editar. La comisión con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			comisionActual.setIdioma(comision.getIdioma());
			comisionActual.setJustificacion(comision.getJustificacion());
			comisionActual.setLugar(comision.getLugar());
			comisionActual.setFechaInicio(comision.getFechaInicio());
			comisionActual.setFechaFin(comision.getFechaFin());
			comisionActual.setResolucion(comision.getResolucion());
			comisionActual.setRespuestaDevolucion(comision.getRespuestaDevolucion());
			comisionActual.setFechaResolucion(comision.getFechaResolucion());
			
			comisionActulizado = comisionService.save(comisionActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar la comisión en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		
		response.put("mensaje", "La comisión ha sido actualizada con éxito!");
		response.put("comision", comisionActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	//ELIMINA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@DeleteMapping("/comisiones/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?>  delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			comisionService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "No se pudo eliminar la comisión en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "La comisión ha sido eliminada con éxito!");
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.OK);
	}
	

	//FILTAR POR LUGAR
	@Secured({"ADMIN", "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/comisiones/filtrar-lugar-comisiones/{lugar}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filtrarComisiones(@PathVariable String lugar) {
		
		List<Comision> comision =  new ArrayList<Comision>(); 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			comision = comisionService.findByLugarContainingIgnoreCase(lugar);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (comision.size() == 0) {
			response.put("mensaje", "Error: No existen Comisiones con lugar : ".concat(lugar.toString()).concat(" en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Comision>>(comision, HttpStatus.OK);
		
	} 
	
	
}
