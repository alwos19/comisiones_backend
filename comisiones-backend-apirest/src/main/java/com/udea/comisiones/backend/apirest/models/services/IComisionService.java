package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.Comision;
import com.udea.comisiones.backend.apirest.models.entity.Documento;

public interface IComisionService {
	
	public List<Comision> findAll();
	
	public Comision findById(Long id);
	
	public Comision save(Comision comision);
	
	public void delete(Long id);
	

}
