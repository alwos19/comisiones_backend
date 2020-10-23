package com.udea.comisiones.backend.apirest.models.dao;


import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Comision;

public interface IComisionDao extends CrudRepository<Comision, Long>{
	
	public List<Comision> findByLugarContainingIgnoreCase(String lugar);
	
	
}
