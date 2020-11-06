package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Departamento;

public interface IDepartamentoDao extends CrudRepository<Departamento, Long>{
	
	public Departamento findByNombreIgnoreCase(String nombre);
	

}
