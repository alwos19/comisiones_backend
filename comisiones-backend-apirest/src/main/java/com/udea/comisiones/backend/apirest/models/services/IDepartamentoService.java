package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import com.udea.comisiones.backend.apirest.models.entity.Departamento;

public interface IDepartamentoService {
	
public List<Departamento> findAll();
	
	public Departamento findById(Long id);
	
	public Departamento save(Departamento departamento);

	public List<Departamento> findByNombreIgnoreCase(String nombre);
}
