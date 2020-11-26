package com.udea.comisiones.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.Documento;

public interface IDocumentoDao extends JpaRepository<Documento, Long>{

	public List<Documento> findByNombreStartingWithIgnoreCase(String nombre);
}
