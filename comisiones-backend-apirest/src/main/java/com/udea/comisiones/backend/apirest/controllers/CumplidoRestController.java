package com.udea.comisiones.backend.apirest.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
	
	//
	
	@GetMapping("/cumplidos")
	public List<Cumplido> index(){
		return cumplidoService.findAll();
	}
	
	@GetMapping("/cumplidos/{id}")
	public Cumplido show(@PathVariable Long id) {
		return cumplidoService.findById(id);
	}
	
	@PostMapping("/cumplidos")
	@ResponseStatus(HttpStatus.CREATED)
	public Cumplido create(@RequestBody Cumplido cumplido) {
		return cumplidoService.save(cumplido);
	}
	
	@PutMapping("/cumplidos/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public Cumplido update(@RequestBody Cumplido cumplido, @PathVariable Long id) {
		Cumplido cumplidoActual = cumplidoService.findById(id);
		
		cumplidoActual.setNombre(cumplido.getNombre());
		cumplidoActual.setDescripcion(cumplido.getDescripcion());
		cumplidoActual.setCorreo(cumplido.getCorreo());
		cumplidoActual.setFechaConfirmacion(cumplido.getFechaConfirmacion());
		cumplidoActual.setFechaEnvio(cumplido.getFechaEnvio());
		cumplidoActual.setInformacionComplementaria(cumplido.getInformacionComplementaria());
		
		return cumplidoService.save(cumplidoActual);
	}
	
	@DeleteMapping("/cumplidos/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		cumplidoService.delete(id);
	}

}
