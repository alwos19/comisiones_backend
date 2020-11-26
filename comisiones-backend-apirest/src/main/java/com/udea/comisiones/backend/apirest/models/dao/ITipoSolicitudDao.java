package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;

public interface ITipoSolicitudDao extends JpaRepository<TipoSolicitud, Long>{
	
	public TipoSolicitud findByNombreIgnoreCase(String nombre);
	
}
