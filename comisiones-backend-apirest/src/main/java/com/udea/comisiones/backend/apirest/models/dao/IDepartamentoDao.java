package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Departamento;

public interface IDepartamentoDao extends JpaRepository<Departamento, Long>{
	
	public Departamento findByNombreIgnoreCase(String nombre);
	

}
