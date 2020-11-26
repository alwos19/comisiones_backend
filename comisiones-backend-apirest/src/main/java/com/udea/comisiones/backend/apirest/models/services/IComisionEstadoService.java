package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;

public interface IComisionEstadoService {
	
	public List<ComisionEstado> findAll();
	
	public Page<ComisionEstado> findAll(Pageable pageable);
	
	public ComisionEstado findById(Long id);
	
	public ComisionEstado save(ComisionEstado estado);
	
	public void delete(Long id);

}
