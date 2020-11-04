package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.Comision;
import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;
import com.udea.comisiones.backend.apirest.models.entity.Estado;

public interface IComisionEstadoService {
	
	public List<ComisionEstado> findAll();
	
	public ComisionEstado findById(Long id);
	
	public ComisionEstado save(ComisionEstado estado);
	
	public void delete(Long id);
	
	public List<ComisionEstado> findByComision(Comision comision);
	
	public List<ComisionEstado> findByEstado(Estado estado);

}
