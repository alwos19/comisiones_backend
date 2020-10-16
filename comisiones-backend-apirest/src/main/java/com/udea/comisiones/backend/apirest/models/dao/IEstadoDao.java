package com.udea.comisiones.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Estado;

public interface IEstadoDao extends CrudRepository<Estado, Long>{

}
