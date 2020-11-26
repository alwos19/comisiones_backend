package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Estado;

public interface IEstadoDao extends JpaRepository<Estado, Long>{
	
	public Estado findByNombreIgnoreCase(String nombre);

}
