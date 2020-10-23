package com.udea.comisiones.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Facultad;

public interface IFacultadDao extends CrudRepository<Facultad, Long>{

	public List<Facultad> findByNombreIgnoreCase(String nombre);
}
