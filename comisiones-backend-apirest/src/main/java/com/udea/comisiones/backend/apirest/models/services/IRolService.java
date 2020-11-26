package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udea.comisiones.backend.apirest.models.entity.Rol;

public interface IRolService {
	
	public List<Rol> findAll();
	
	public Page<Rol> findAll(Pageable pageable);
	
	public Rol findById(Long id);
	
	public Rol save(Rol rol);
	
	public Rol findByNombreIgnoreCase(String nombre);

}
