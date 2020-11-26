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

import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;
import com.udea.comisiones.backend.apirest.models.services.ITipoSolicitudService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TipoSolicitudRestController {
	
	@Autowired
	private ITipoSolicitudService tipoSolicitudService;
	
	//CONSULTA LOS TIPOS DE SOLICITUD
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/tipos-solicitud")
	public List<TipoSolicitud> index(){
		return tipoSolicitudService.findAll();
	}

	//CONSULTA LOS TIPOS DE SOLICITUD
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/tipos-solicitud/page/{page}")
	public Page<TipoSolicitud> index(@PathVariable Integer page){
		return tipoSolicitudService.findAll(PageRequest.of(page, 10));
	}
	

	
	//CONSULTA UN TIPO DE SOLICITUD POR ID
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/tipos-solicitud/{id}")
	public ResponseEntity<?>  show(@PathVariable Long id) {
		
		TipoSolicitud tipoSolicitud =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			tipoSolicitud = tipoSolicitudService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (tipoSolicitud == null) {
			response.put("mensaje", "Error: El tipo de solicitud con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoSolicitud>(tipoSolicitud, HttpStatus.OK);
		
	}
	
	//CREA UN TIPO DE SOLICITUD
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@PostMapping("/tipos-solicitud")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody TipoSolicitud tipoSolicitud, BindingResult result) {
		
		TipoSolicitud tipoSolicitudNuevo =  null; 
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
			tipoSolicitudNuevo = tipoSolicitudService.save(tipoSolicitud);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El tipo de solicitud ha sido creado con éxito!");
		response.put("tipoSolicitud", tipoSolicitudNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA UN TIPO DE SOLICITUD
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@PutMapping("/tipos-solicitud/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid  @RequestBody TipoSolicitud tipoSolicitud, BindingResult result, @PathVariable Long id) {
		
		TipoSolicitud tipoSolicitudActual = tipoSolicitudService.findById(id);  
		Map<String, Object> response = new HashMap<>();
		TipoSolicitud tipoSolicitudActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (tipoSolicitudActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El tipo de solicitud con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			tipoSolicitudActual.setNombre(tipoSolicitud.getNombre());
			tipoSolicitudActual.setDescripcion(tipoSolicitud.getDescripcion());
					
			tipoSolicitudActulizado = tipoSolicitudService.save(tipoSolicitudActual);
							
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el tipo de solicitud en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El tipo de solicitud ha sido actualizado con éxito!");
		response.put("tipoSolicitud", tipoSolicitudActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	//ELIMINA UN TIPO DE SOLICITUD
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA"})
	@DeleteMapping("/tipos-solicitud/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			tipoSolicitudService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "No se pudo eliminar el tipo de solicitud en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El tipo de solicitud ha sido eliminado con éxito!");
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.OK);
	}
	
	//FILTRA POR NOMBRE
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/tipos-solicitud/filtrar-tipos-solicitud/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filtrarProductos(@PathVariable String nombre) {
		
		TipoSolicitud tipoSolicitud =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			tipoSolicitud = tipoSolicitudService.findByNombreIgnoreCase(nombre);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (tipoSolicitud == null) {
			response.put("mensaje", "Error: El tipo de solicitud con nombre: ".concat(nombre.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<TipoSolicitud>(tipoSolicitud, HttpStatus.OK);
	} 

}
