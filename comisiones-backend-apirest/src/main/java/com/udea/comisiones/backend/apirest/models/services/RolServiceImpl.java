package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.IRolDao;
import com.udea.comisiones.backend.apirest.models.entity.Rol;

@Service
public class RolServiceImpl implements IRolService{
	
	@Autowired
	private IRolDao rolDao;
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public List<Rol> findAll() {
		
		return (List<Rol>) rolDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Rol findById(Long id) {
		return rolDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Rol save(Rol usuario) {
		return rolDao.save(usuario);
	}

	@Override
	public Rol findByNombreIgnoreCase(String nombre) {
		return rolDao.findByNombreIgnoreCase(nombre);
	}


}
