package com.udea.comisiones.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Departamento;

public interface IDepartamentoDao extends CrudRepository<Departamento, Long>{
	
	public List<Departamento> findByNombreIgnoreCase(String nombre);
	

}
