package com.udea.comisiones.backend.apirest.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Documento;

public interface IDocumentoDao extends CrudRepository<Documento, Long>{

}
