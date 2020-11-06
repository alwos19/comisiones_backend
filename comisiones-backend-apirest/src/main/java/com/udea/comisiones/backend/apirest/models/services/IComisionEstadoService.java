package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;

public interface IComisionEstadoService {
	
	public List<ComisionEstado> findAll();
	
	public ComisionEstado findById(Long id);
	
	public ComisionEstado save(ComisionEstado estado);
	
	public void delete(Long id);

}
