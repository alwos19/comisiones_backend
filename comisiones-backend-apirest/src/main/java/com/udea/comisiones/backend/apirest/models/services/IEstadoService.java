package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.Estado;

public interface IEstadoService {
	
	public List<Estado> findAll();
	
	public Estado findById(Long id);
	
	public Estado save(Estado estado);
	
	public List<Estado> findByNombreIgnoreCase(String nombre);

}
