package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;

public interface ITipoSolicitudDao extends CrudRepository<TipoSolicitud, Long>{
	
	public TipoSolicitud findByNombreIgnoreCase(String nombre);
	
}
