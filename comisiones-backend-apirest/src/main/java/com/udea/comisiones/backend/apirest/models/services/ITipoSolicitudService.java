package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;

public interface ITipoSolicitudService {
	
	public List<TipoSolicitud> findAll();
	
	public TipoSolicitud findById(Long id);
	
	public TipoSolicitud save(TipoSolicitud tipoSolicitud);
	
	public void delete(Long id);
	
	public List<TipoSolicitud> findByNombreIgnoreCase(String nombre);

}
