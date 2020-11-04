package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.IComisionEstadoDao;
import com.udea.comisiones.backend.apirest.models.entity.Comision;
import com.udea.comisiones.backend.apirest.models.entity.ComisionEstado;
import com.udea.comisiones.backend.apirest.models.entity.Estado;

@Service
public class ComisionEstadoServiceImpl implements IComisionEstadoService{

	@Autowired
	private IComisionEstadoDao comisionEstadoDao;
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public List<ComisionEstado> findAll() {
		return (List<ComisionEstado>) comisionEstadoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public ComisionEstado findById(Long id) {
		return comisionEstadoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public ComisionEstado save(ComisionEstado estado) {
		return comisionEstadoDao.save(estado);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		comisionEstadoDao.deleteById(id);
		
	}
	
	@Override
	@Transactional(readOnly = true)
	public List<ComisionEstado> findByComision(Comision comision){
		return comisionEstadoDao.findByComision(comision);
	}

	@Override
	public List<ComisionEstado> findByEstado(Estado estado) {
		return comisionEstadoDao.findByEstado(estado);
	}

}
