package com.udea.comisiones.backend.apirest.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.udea.comisiones.backend.apirest.models.dao.IFacultadDao;
import com.udea.comisiones.backend.apirest.models.entity.Facultad;

@Service
public class FacultadServiceImpl implements IFacultadService{

	@Autowired
	private IFacultadDao facultadDao;
	
	//
	
	@Override
	@Transactional(readOnly = true)
	public List<Facultad> findAll() {
		return (List<Facultad>) facultadDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Facultad findById(Long id) {
		return facultadDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Facultad save(Facultad facultad) {
		return facultadDao.save(facultad);
	}

	@Override
	public Facultad findByNombreIgnoreCase(String nombre) {
		return facultadDao.findByNombreIgnoreCase(nombre);
	}



}
