package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Rol;

public interface IRolDao extends JpaRepository<Rol, Long>{
	
	public Rol findByNombreIgnoreCase(String nombre);

}
