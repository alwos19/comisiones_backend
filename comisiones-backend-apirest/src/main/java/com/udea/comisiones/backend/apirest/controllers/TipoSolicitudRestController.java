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

import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;
import com.udea.comisiones.backend.apirest.models.services.ITipoSolicitudService;

@CrossOrigin (origins= {"http://localhost:4200"})
@RestController
@RequestMapping("/api")
public class TipoSolicitudRestController {
	
	@Autowired
	private ITipoSolicitudService tipoSolicitudService;
	
	//
	
	@GetMapping("/tipos-solicitud")
	public List<TipoSolicitud> index(){
		return tipoSolicitudService.findAll();
	}
	
	@GetMapping("/tipos-solicitud/{id}")
	public TipoSolicitud show(@PathVariable Long id) {
		return tipoSolicitudService.findById(id);
	}
	
	@PostMapping("/tipos-solicitud")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoSolicitud create(@RequestBody TipoSolicitud tipoSolicitud) {
		return tipoSolicitudService.save(tipoSolicitud);
	}
	
	@PutMapping("/tipos-solicitud/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public TipoSolicitud update(@RequestBody TipoSolicitud tipoSolicitud, @PathVariable Long id) {
		TipoSolicitud tipoSolicitudActual = tipoSolicitudService.findById(id);
		
		tipoSolicitudActual.setNombre(tipoSolicitud.getNombre());
		tipoSolicitudActual.setDescripcion(tipoSolicitud.getDescripcion());
		
		return tipoSolicitudService.save(tipoSolicitudActual);
	}
	
	@DeleteMapping("/tipos-solicitud/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		tipoSolicitudService.delete(id);
	}
	
	@GetMapping("/tipos-solicitud/filtrar-tipos-solicitud/{nombre}")
	@ResponseStatus(HttpStatus.OK)
	public List<TipoSolicitud> filtrarProductos(@PathVariable String nombre) {
		return tipoSolicitudService.findByNombreIgnoreCase(nombre);
	} 

}
