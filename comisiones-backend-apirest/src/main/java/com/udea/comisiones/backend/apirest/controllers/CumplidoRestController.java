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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.udea.comisiones.backend.apirest.models.entity.Cumplido;
import com.udea.comisiones.backend.apirest.models.services.ICumplidoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class CumplidoRestController {
	
	@Autowired
	private ICumplidoService cumplidoService;
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN", "ROLE_VICERRECTORIA", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR",  "ROLE_USUARIO"})
	@GetMapping("/cumplidos")
	public List<Cumplido> index(){
		return cumplidoService.findAll();
	}
	
	//CONSULTA TODOS
	@Secured({"ROLE_ADMIN", "ROLE_VICERRECTORIA", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR",  "ROLE_USUARIO"})
	@GetMapping("/cumplidos/page/{page}")
	public Page<Cumplido> index(@PathVariable Integer page){
		return cumplidoService.findAll(PageRequest.of(page, 10));
	}
	
	//CONSULTA POR ID
	@Secured({"ROLE_ADMIN", "ROLE_VICERRECTORIA", "ROLE_DECANO", "ROLE_DIRECTOR", "ROLE_SECRETARIA_DECANO", "ROLE_SECRETARIA_DIRECTOR",  "ROLE_USUARIO"})
	@GetMapping("/cumplidos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Cumplido cumplido =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			cumplido = cumplidoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (cumplido == null) {
			response.put("mensaje", "Error: El cumplido con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Cumplido>(cumplido, HttpStatus.OK);
	}
	
	//CREA
	@Secured({"ROLE_ADMIN", "ROLE_USUARIO"})
	@PostMapping("/cumplidos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Cumplido cumplido, BindingResult result) {
		
		Cumplido cumplidoNuevo =  null; 
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
			cumplidoNuevo = cumplidoService.save(cumplido);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El cumplido ha sido creado con éxito!");
		response.put("cumplido", cumplidoNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA
	@Secured({"ROLE_ADMIN", "ROLE_USUARIO"})
	@PutMapping("/cumplidos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Cumplido cumplido, @PathVariable Long id, BindingResult result) {
		
		Cumplido cumplidoActual = cumplidoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Cumplido cumplidoActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (cumplidoActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El usuario con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			cumplidoActual.setNombre(cumplido.getNombre());
			cumplidoActual.setDescripcion(cumplido.getDescripcion());
			cumplidoActual.setCorreo(cumplido.getCorreo());
			cumplidoActual.setFechaConfirmacion(cumplido.getFechaConfirmacion());
			cumplidoActual.setFechaEnvio(cumplido.getFechaEnvio());
			cumplidoActual.setInformacionComplementaria(cumplido.getInformacionComplementaria());
			
			cumplidoActulizado = cumplidoService.save(cumplidoActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el usuario en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("cumplido", cumplidoActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	//ELIMINA
	@Secured({"ROLE_ADMIN", "ROLE_USUARIO"})
	@DeleteMapping("/cumplidos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			cumplidoService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "No se pudo eliminar el cumplido en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El cumplido ha sido eliminado con éxito!");
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.OK);
	}

}
