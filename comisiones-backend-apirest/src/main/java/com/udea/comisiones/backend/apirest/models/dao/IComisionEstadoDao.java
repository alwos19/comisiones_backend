package com.udea.comisiones.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Comision;
import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;
import com.udea.comisiones.backend.apirest.models.entity.Estado;

public interface IComisionEstadoDao extends CrudRepository<ComisionEstado, Long>{
	
	public List<ComisionEstado> findByComision(Comision comision);
	
	public List<ComisionEstado> findByEstado(Estado estado);

}
