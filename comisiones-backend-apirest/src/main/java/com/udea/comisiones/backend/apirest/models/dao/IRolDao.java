package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Rol;

public interface IRolDao extends CrudRepository<Rol, Long>{
	
	public Rol findByNombreIgnoreCase(String nombre);

}
