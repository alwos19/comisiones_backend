package com.udea.comisiones.backend.apirest.models.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Facultad;

public interface IFacultadDao extends JpaRepository<Facultad, Long>{

	public Facultad findByNombreIgnoreCase(String nombre);
}
