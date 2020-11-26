package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udea.comisiones.backend.apirest.models.entity.Estado;

public interface IEstadoService {
	
	public List<Estado> findAll();
	
	public Page<Estado> findAll(Pageable pageable);
	
	public Estado findById(Long id);
	
	public Estado save(Estado estado);
	
	public Estado findByNombreIgnoreCase(String nombre);

}
