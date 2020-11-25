package com.udea.comisiones.backend.apirest.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
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

import com.udea.comisiones.backend.apirest.models.entity.Documento;
import com.udea.comisiones.backend.apirest.models.services.IDocumentoService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class DocumentoRestController {
	
	@Autowired
	private IDocumentoService documentoService;
	
	///CONSULTA TODOS
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/documentos")
	public List<Documento> index(){
		return documentoService.findAll();
	}
	
	//CONSULTA POR ID
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/documentos/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Documento documento =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			documento = documentoService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (documento == null) {
			response.put("mensaje", "Error: El documento con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Documento>(documento, HttpStatus.OK);
	}
	
	//CREA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@PostMapping("/documentos")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@Valid @RequestBody Documento documento, BindingResult result) {
		
		Documento documentoNuevo =  null; 
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
			documentoNuevo = documentoService.save(documento);	
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El documento ha sido creado con éxito!");
		response.put("documento", documentoNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	//ACTUALIZA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@PutMapping("/documentos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Documento documento, BindingResult result, @PathVariable Long id) {
		
		Documento documentoActual = documentoService.findById(id);
		Map<String, Object> response = new HashMap<>();
		Documento documentoActulizado = null;
		
		if(result.hasErrors()){
			
			List<String> errors = result.getFieldErrors()
					.stream()  
					.map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage()) 
					.collect(Collectors.toList()); 
			
			response.put("error", errors);
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.BAD_REQUEST);   
		}
		
		if (documentoActual == null) {
			response.put("mensaje", "Error: No se puede Editar. El documento con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		//---
		try {
			documentoActual.setNombre(documento.getNombre());
			documentoActual.setEsCumplido(documento.getEsCumplido());
			documentoActual.setEsAnexo(documento.getEsAnexo());
			documentoActual.setComision(documento.getComision());
			
			documentoActulizado = documentoService.save(documentoActual);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el documento en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		
		response.put("mensaje", "El documento ha sido actualizado con éxito!");
		response.put("documento", documentoActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	//ELIMINA
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@DeleteMapping("/documentos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			documentoService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "No se pudo eliminar el documento en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El documento ha sido eliminado con éxito!");
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.OK);
		
	}
	
	//FILTRA POR NOMBRE
	@Secured({"ROLE_ADMIN",  "ROLE_COORDINADOR", "ROLE_SECRETARIA", "ROLE_PROFESOR", "ROLE_ESTUDIANTE"})
	@GetMapping("/documentos/filtrar-nombre-documentos/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filtrarDocumentos(@PathVariable String nombre) {
		
		List<Documento> documento =  new ArrayList<Documento>(); 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			documento = documentoService.findByNombreIgnoreCase(nombre);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (documento.size() == 0) {
			response.put("mensaje", "Error: No hay documentos que contengan: ".concat(nombre.toString()).concat(" en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<List<Documento> >(documento, HttpStatus.OK);
	} 

}
