package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udea.comisiones.backend.apirest.models.entity.Facultad;

public interface IFacultadService {
	
	public List<Facultad> findAll();
	
	public Page<Facultad> findAll(Pageable pageable);
	
	public Facultad findById(Long id);
	
	public Facultad save(Facultad facultad);
	
	public Facultad findByNombreIgnoreCase(String nombre);

}
