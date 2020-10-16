package com.udea.comisiones.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Facultad;

public interface IFacultadDao extends CrudRepository<Facultad, Long>{

}
