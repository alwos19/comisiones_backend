package com.udea.comisiones.backend.apirest.models.dao;



import org.springframework.data.repository.CrudRepository;

import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;

public interface IComisionEstadoDao extends CrudRepository<ComisionEstado, Long>{


}
