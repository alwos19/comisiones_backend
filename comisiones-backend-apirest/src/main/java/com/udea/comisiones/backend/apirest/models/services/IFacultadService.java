package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.Facultad;

public interface IFacultadService {
	
	public List<Facultad> findAll();
	
	public Facultad findById(Long id);
	
	public Facultad save(Facultad facultad);
	
	public List<Facultad> findByNombreIgnoreCase(String nombre);

}
