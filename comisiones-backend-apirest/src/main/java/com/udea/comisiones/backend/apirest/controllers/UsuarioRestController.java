package com.udea.comisiones.backend.apirest.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

import com.udea.comisiones.backend.apirest.models.entity.Usuario;
import com.udea.comisiones.backend.apirest.models.services.IUsuarioService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class UsuarioRestController {
	
	@Autowired
	private IUsuarioService usuarioService;
	
	//
	
	//CONSULTA LOS USUARIOS
	@GetMapping("/usuarios")
	public List<Usuario> index(){
		return usuarioService.findAll();
	}
	
	
	
	//CONSULTA UN USUARIO POR ID
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Usuario usuario =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			usuario = usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (usuario == null) {
			response.put("mensaje", "Error: El cliente con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
		
	}
	
	
	//CREA UN USUARIO
	@PostMapping("/usuarios")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> create(@RequestBody Usuario usuario) {
		
		Usuario usuarioNuevo =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			usuarioNuevo = usuarioService.save(usuario);		
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar el insert en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR); 
		}
		//---
		
		response.put("mensaje", "El Usuario ha sido creado con éxito!");
		response.put("usuario", usuarioNuevo);	
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);
	}
	
	
	
	//ACTUALIZA UN USUARIO
	@PutMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
		
		Usuario usuarioConsulta = usuarioService.findById(id);  
		Map<String, Object> response = new HashMap<>();
		Usuario usuarioActulizado = null;
		
		
		if (usuarioConsulta == null) {
			response.put("mensaje", "Error: No se puede Editar. El cliente con el ID: ".concat(id.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		//---
		try {
			usuarioConsulta.setTipoIdentificacion(usuario.getTipoIdentificacion());
			usuarioConsulta.setIdentificacion(usuario.getIdentificacion());
			usuarioConsulta.setNombre(usuario.getNombre());
			usuarioConsulta.setApellido(usuario.getApellido());
			usuarioConsulta.setEmail(usuario.getEmail());
			usuarioConsulta.setContrasena(usuario.getContrasena());
			usuarioConsulta.setCreateAt(usuario.getCreateAt());
			usuarioConsulta.setEstado(usuario.getEstado());
			
			usuarioActulizado = usuarioService.save(usuarioConsulta);
					
		}catch(DataAccessException e) {
			response.put("mensaje", "No se pudo actualizar el usuario en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---

		
		response.put("mensaje", "El Usuario ha sido actualizado con éxito!");
		response.put("usuario", usuarioActulizado);
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.CREATED);	
	}
	
	
	
	//ELIMINA UN USUARIO
	@DeleteMapping("/usuarios/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			usuarioService.delete(id);
			
		} catch(DataAccessException e) {
			
			response.put("mensaje", "No se pudo eliminar el usuario en la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);   
		}
		//---
		
		response.put("mensaje", "El Usuario ha sido eliminado con éxito!");
		
		return new ResponseEntity< Map<String, Object> >(response, HttpStatus.OK);
		
	}
	
	
	
	
	//FILTRA USUARIOS POR APELLIDO
	@GetMapping("/usuarios/filtrar-nombre-usuarios/{apellido}")
	@ResponseStatus(HttpStatus.OK)
	public List<Usuario> filtrarUsuariosByNombre(@PathVariable String apellido) {
		return usuarioService.findByApellidoIgnoreCase(apellido);
	}
	
	
	
	//FILTRA UN USUARIO POR IDENTIFICACION
	@GetMapping("/usuarios/filtrar-identificacion-usuarios/{identificacion}")
	@ResponseStatus(HttpStatus.OK)
	public ResponseEntity<?> filtrarUsuariosByIdentificacion(@PathVariable Integer identificacion) {
		
		Usuario usuario =  null; 
		Map<String, Object> response = new HashMap<>();
		
		//---
		try {
			usuario = usuarioService.findByIdentificacion(identificacion);
		} catch(DataAccessException e) {
			response.put("mensaje", "No se pudo realizar la consulta a la Base de Datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.INTERNAL_SERVER_ERROR);    
		}
		//---
		
		if (usuario == null) {
			response.put("mensaje", "Error: El cliente con la Identificación: ".concat(identificacion.toString()).concat(" NO existe en la Base de Datos"));
			return new ResponseEntity< Map<String, Object> >(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	

}
