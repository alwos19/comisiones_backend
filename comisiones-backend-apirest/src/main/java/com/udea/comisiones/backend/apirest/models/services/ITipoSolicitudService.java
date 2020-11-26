package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.udea.comisiones.backend.apirest.models.entity.TipoSolicitud;

public interface ITipoSolicitudService {
	
	public List<TipoSolicitud> findAll();
	
	public Page<TipoSolicitud> findAll(Pageable pageable);
	
	public TipoSolicitud findById(Long id);
	
	public TipoSolicitud save(TipoSolicitud tipoSolicitud);
	
	public void delete(Long id);
	
	public TipoSolicitud findByNombreIgnoreCase(String nombre);

}
