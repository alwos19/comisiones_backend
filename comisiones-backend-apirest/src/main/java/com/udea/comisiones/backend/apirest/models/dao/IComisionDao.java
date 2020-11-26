package com.udea.comisiones.backend.apirest.models.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Comision;

public interface IComisionDao extends JpaRepository<Comision, Long>{
	
	public List<Comision> findByLugarContainingIgnoreCase(String lugar);
	
	
}
