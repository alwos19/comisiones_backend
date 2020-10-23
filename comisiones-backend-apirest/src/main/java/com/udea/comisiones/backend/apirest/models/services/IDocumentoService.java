package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.Documento;

public interface IDocumentoService {
	
	public List<Documento> findAll();
	
	public Documento findById(Long id);
	
	public Documento save(Documento documento);
	
	public void delete(Long id);
	
	public List<Documento> findByNombreIgnoreCase(String nombre);

}
