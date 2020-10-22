package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.ICumplidoDao;
import com.udea.comisiones.backend.apirest.models.entity.Cumplido;

@Service
public class CumplidoServiceImpl implements ICumplidoService{
	
	@Autowired
	private ICumplidoDao cumplidoDao;
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public List<Cumplido> findAll() {
		
		return (List<Cumplido>) cumplidoDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Cumplido findById(Long id) {
		return cumplidoDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Cumplido save(Cumplido usuario) {
		return cumplidoDao.save(usuario);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		cumplidoDao.deleteById(id);
		
	}

}
